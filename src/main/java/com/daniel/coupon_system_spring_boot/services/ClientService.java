package com.daniel.coupon_system_spring_boot.services;

import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring_boot.repos.CompanyRepository;
import com.daniel.coupon_system_spring_boot.repos.CouponRepository;
import com.daniel.coupon_system_spring_boot.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by danielR on 15/11/2022
 */
public abstract class ClientService {
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CompanyRepository companyRepository;

    public abstract boolean login(String email,String password) throws CouponCostumeException;

}
