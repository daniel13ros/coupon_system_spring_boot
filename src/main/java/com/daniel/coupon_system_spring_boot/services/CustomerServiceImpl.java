package com.daniel.coupon_system_spring_boot.services;

import com.daniel.coupon_system_spring_boot.beans.Category;
import com.daniel.coupon_system_spring_boot.beans.Company;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.beans.Customer;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring_boot.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
@Service
public class CustomerServiceImpl extends ClientService implements CustomerService{


    @Override
    public boolean login(String email, String password) throws CouponCostumeException {
        if (!this.customerRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponCostumeException(ErrMsg.EMAIL_OR_PASS_WRONG);
        }
        Customer customer = this.customerRepository.findByEmailAndPassword(email, password);
        /*int customerId = customer.getId();*/
        return true;
    }

    @Override
    public void purchaseCoupon(int customerId,Coupon coupon,int couponId) throws CouponCostumeException {
        if(this.customerRepository.findById(customerId).get().getCoupons().contains(coupon)){
            throw new CouponCostumeException(ErrMsg.COUPON_IS_EXIST_IN_CUSTOMER);
        }
        if(this.couponRepository.findById(coupon.getId()).get().getAmount()==0){
            throw new CouponCostumeException(ErrMsg.COUPON_AMOUNT_IS_ZERO);
        }
        if(this.couponRepository.findById(coupon.getId()).get().getEndDate().before(Date.valueOf(LocalDate.now()))){
            throw new CouponCostumeException(ErrMsg.COUPON_EXPIRED);
        }
        Customer c = this.customerRepository.findById(customerId).orElseThrow();
        coupon.setAmount(coupon.getAmount()-1);
        coupon.setCompany(couponRepository.findById(couponId).get().getCompany());
        c.getCoupons().add(coupon);
        this.customerRepository.save(c);
        this.couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) {
        return this.customerRepository.findById(customerId).get().getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId,Category category) {
        return this.couponRepository.findByCustomerAndCategory(customerId,category.name());
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId,double maxPrice) {
        return this.couponRepository.findByCustomerAndPrice(customerId, (int) maxPrice);
    }

    @Override
    public void getCustomerDetails(int customerId) {
        System.out.println("id :"+customerRepository.findById(customerId).get().getId());
        System.out.println("first name :"+customerRepository.findById(customerId).get().getFirstName());
        System.out.println("last name :"+customerRepository.findById(customerId).get().getLastName());
        System.out.println("email :"+customerRepository.findById(customerId).get().getEmail());
        System.out.println("password :"+customerRepository.findById(customerId).get().getPassword());
        System.out.println("coupons :");
        customerRepository.findById(customerId).get().getCoupons().forEach(System.out::println);;
    }
}
