package com.daniel.coupon_system_spring_boot.services;

import com.daniel.coupon_system_spring_boot.beans.Category;
import com.daniel.coupon_system_spring_boot.beans.Company;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring_boot.exceptions.ErrMsg;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {




    @Override
    public void addCoupon(int companyId,Coupon coupon) throws CouponCostumeException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
            throw new CouponCostumeException(ErrMsg.COUPON_EXIST_BT_TITLE);
        }
        coupon.setCompany(this.companyRepository.findById(companyId).orElseThrow());
        couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon ,int companyId) throws CouponCostumeException {
        if (coupon.getId() != couponRepository.findById(couponId).get().getId()) {
            throw new CouponCostumeException(ErrMsg.CANNOT_UPDATE_ID_COUPON);
        }
        if (companyId!= couponRepository.findById(couponId).get().getCompany().getId()) {
            throw new CouponCostumeException(ErrMsg.CANNOT_UPDATE_ID_COUPON);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponCostumeException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponCostumeException(ErrMsg.COUPON_NOT_EXIST);
        }
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) {
        return couponRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId,Category category) {
        return couponRepository.findByCategoryAndCompanyId(category, companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId,double maxPrice) {
        return couponRepository.findByPriceLessThanAndCompanyId(maxPrice, companyId);
    }

    @Override
    public void getCompanyDetails(int companyId) {
        System.out.println("id :"+companyRepository.findById(companyId).get().getId());
        System.out.println("name :"+companyRepository.findById(companyId).get().getName());
        System.out.println("email :"+companyRepository.findById(companyId).get().getEmail());
        System.out.println("password :"+companyRepository.findById(companyId).get().getPassword());
        System.out.println("coupons :");
        companyRepository.findById(companyId).get().getCoupons().forEach(System.out::println);

    }

    @Override
    public boolean login(String email, String password) throws CouponCostumeException {
        if (!companyRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponCostumeException(ErrMsg.EMAIL_OR_PASS_WRONG);
        }
        Company company = companyRepository.findByEmailAndPassword(email, password);
        /*int companyId = company.getId();*/
        return true;
    }
}
