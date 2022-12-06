package com.daniel.coupon_system_spring_boot.services;

import com.daniel.coupon_system_spring_boot.beans.Category;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.beans.Customer;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
public interface CustomerService {

    void purchaseCoupon(int customerId,Coupon coupon,int couponId) throws CouponCostumeException;

    List<Coupon> getCustomerCoupons(int customerId);

    List<Coupon> getCustomerCoupons(int customerId,Category category);

    List<Coupon> getCustomerCoupons(int customerId,double maxPrice);

    void getCustomerDetails(int customerId);
}
