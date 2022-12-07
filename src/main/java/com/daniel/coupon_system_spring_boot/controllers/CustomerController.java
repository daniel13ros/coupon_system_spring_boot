package com.daniel.coupon_system_spring_boot.controllers;

import com.daniel.coupon_system_spring_boot.beans.Category;
import com.daniel.coupon_system_spring_boot.beans.ClientType;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring_boot.exceptions.ErrMsg;
import com.daniel.coupon_system_spring_boot.services.AdminService;
import com.daniel.coupon_system_spring_boot.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by danielR on 16/11/2022
 */

@RestController
@RequestMapping("api/customers")
public class CustomerController extends ClientController   {

    @PostMapping("{customerId}/purchase/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int customerId,@RequestBody Coupon coupon,@PathVariable int couponId) throws CouponCostumeException {
        customerService.purchaseCoupon(customerId,coupon,couponId);
        System.out.println("Success");
    }

    @GetMapping("{customerId}/coupons")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId) {
        return customerService.getCustomerCoupons(customerId);
    }

    @GetMapping("{customerId}/coupons/category")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId,@RequestParam Category category) {
        return customerService.getCustomerCoupons(customerId,category);
    }

    @GetMapping("{customerId}/coupons/price")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId,@RequestParam double maxPrice) {
        return customerService.getCustomerCoupons(customerId,maxPrice);
    }

    @GetMapping("{customerId}/details")
    public void getCustomerDetails(@PathVariable int customerId) {
        customerService.getCustomerDetails(customerId);
    }

    @PostMapping("/login/{email}&{password}")
    public boolean login(@RequestParam String email,@RequestParam String password) throws CouponCostumeException {
        customerService = (CustomerService) loginManager.login(email, password, ClientType.Customer);
        if (customerService == null) {
            throw new CouponCostumeException(ErrMsg.EMAIL_OR_PASS_WRONG);
        }
        return true;
    }
}
