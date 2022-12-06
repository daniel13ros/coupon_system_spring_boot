package com.daniel.coupon_system_spring_boot.controllers;

import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring_boot.login.LoginManager;
import com.daniel.coupon_system_spring_boot.services.AdminService;
import com.daniel.coupon_system_spring_boot.services.CompanyService;
import com.daniel.coupon_system_spring_boot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by danielR on 16/11/2022
 */


public abstract class ClientController {


    @Autowired
    protected LoginManager loginManager;
    @Autowired
    protected AdminService adminService;

    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected CustomerService customerService;

    public abstract boolean login(String email,String password) throws CouponCostumeException;





}
