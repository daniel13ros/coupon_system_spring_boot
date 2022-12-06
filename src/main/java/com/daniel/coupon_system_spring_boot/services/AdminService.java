package com.daniel.coupon_system_spring_boot.services;

import com.daniel.coupon_system_spring_boot.beans.Company;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.beans.Customer;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;

import java.util.List;
import java.util.Optional;

/**
 * Created by danielR on 15/11/2022
 */
public interface AdminService {

    void addCompany(Company company) throws CouponCostumeException;

    void updateCompany(int id, Company company) throws CouponCostumeException;

    void deleteCompany(int id) throws CouponCostumeException;

    List<Company> getAllCompanies();
    List<Coupon> getAllCoupons();

   Company getSingleCompany(int companyId) throws CouponCostumeException;

    void addCustomer(Customer customer) throws CouponCostumeException;

    void updateCustomer(int customerId, Customer customer) throws CouponCostumeException;

    void deleteCustomer(int id) throws CouponCostumeException;

    List<Customer> getAllCustomers();

    Customer getSingleCustomer(int id) throws CouponCostumeException;

}
