package com.daniel.coupon_system_spring_boot.controllers;

import com.daniel.coupon_system_spring_boot.beans.Category;
import com.daniel.coupon_system_spring_boot.beans.ClientType;
import com.daniel.coupon_system_spring_boot.beans.Company;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring_boot.exceptions.ErrMsg;
import com.daniel.coupon_system_spring_boot.login.LoginManager;
import com.daniel.coupon_system_spring_boot.services.AdminService;
import com.daniel.coupon_system_spring_boot.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by danielR on 16/11/2022
 */
@RestController
@RequestMapping("api/companies")
public class CompanyController extends ClientController {

    @PostMapping("{companyId}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable int companyId, @RequestBody Coupon coupon) throws CouponCostumeException {
        companyService.addCoupon(companyId, coupon);
    }

    @PutMapping("{companyId}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int companyId, @RequestBody Coupon coupon,@PathVariable int couponId) throws CouponCostumeException {
        companyService.updateCoupon(companyId, coupon,couponId);
        System.out.println("Updated");
    }


    @PostMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int couponId) throws CouponCostumeException {
        companyService.deleteCoupon(couponId);
        System.out.println("Deleted");
    }

    @GetMapping("{companyId}/coupons/")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId) {
        return companyService.getCompanyCoupons(companyId);
    }

    @GetMapping("{companyId}/coupons/category")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestParam Category category) {
        return companyService.getCompanyCoupons(companyId, category);
    }

    @GetMapping("{companyId}/coupons/price")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestParam double maxPrice) {
        return companyService.getCompanyCoupons(companyId, maxPrice);
    }

    @GetMapping("{companyId}/details")
    public void getCompanyDetails(@PathVariable int companyId) {
        companyService.getCompanyDetails(companyId);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String email,@RequestParam String password, @RequestParam String clientType ) throws CouponCostumeException {
        companyService = (CompanyService) loginManager.login(email, password, ClientType.valueOf(clientType));
        if (companyService == null) {
            throw new CouponCostumeException(ErrMsg.EMAIL_OR_PASS_WRONG);
        }
        return true;
    }
}
