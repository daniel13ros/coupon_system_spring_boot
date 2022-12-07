package com.daniel.coupon_system_spring_boot.services;

import com.daniel.coupon_system_spring_boot.beans.Category;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
public interface CompanyService {

    void addCoupon(int companyId,Coupon coupon) throws CouponCostumeException;

    void updateCoupon(int companyId, Coupon coupon , int couponId) throws CouponCostumeException;

    void deleteCoupon(int couponId) throws CouponCostumeException;

    List<Coupon> getCompanyCoupons(int companyId);

    List<Coupon> getCompanyCoupons(int companyId,Category category);

    List<Coupon> getCompanyCoupons(int companyId,double maxPrice);

    void getCompanyDetails(int companyId);

}
