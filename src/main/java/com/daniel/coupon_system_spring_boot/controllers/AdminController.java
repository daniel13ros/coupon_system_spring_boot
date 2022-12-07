package com.daniel.coupon_system_spring_boot.controllers;

import com.daniel.coupon_system_spring_boot.beans.ClientType;
import com.daniel.coupon_system_spring_boot.beans.Company;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.beans.Customer;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring_boot.exceptions.ErrMsg;
import com.daniel.coupon_system_spring_boot.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by danielR on 16/11/2022
 */
@RestController
@RequestMapping("api/admin")
public class AdminController extends ClientController {

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponCostumeException {
        adminService.addCompany(company);
    }

    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int companyId, @RequestBody Company company) throws CouponCostumeException {
        adminService.updateCompany(companyId, company);
        System.out.println("Updated");
    }

    @PostMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyId) throws CouponCostumeException {
        adminService.deleteCompany(companyId);
        System.out.println("Deleted");
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@PathVariable int companyId) throws CouponCostumeException {
        return adminService.getSingleCompany(companyId);
    }

    @PostMapping("customers/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponCostumeException {
        adminService.addCustomer(customer);
    }

    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws CouponCostumeException {
        adminService.updateCustomer(customerId, customer);
        System.out.println("Updated");
    }

    @PostMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) throws CouponCostumeException {
        adminService.deleteCustomer(customerId);
        System.out.println("Deleted");
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("coupons")
    public List<Coupon> getAllCoupons() {
        return adminService.getAllCoupons();
    }

    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomer(@PathVariable int customerId) throws CouponCostumeException {
        return adminService.getSingleCustomer(customerId);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String email,@RequestParam String password, @RequestParam String clientType ) throws CouponCostumeException {
        adminService = (AdminService) loginManager.login(email, password, ClientType.valueOf(clientType));
        if (adminService == null) {
            throw new CouponCostumeException(ErrMsg.EMAIL_OR_PASS_WRONG);
        }
        return true;
    }


}

