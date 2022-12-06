package com.daniel.coupon_system_spring_boot.services;

import com.daniel.coupon_system_spring_boot.beans.Company;
import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.beans.Customer;
import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring_boot.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by danielR on 15/11/2022
 */
@Service
public class AdminServiceImpl extends ClientService implements AdminService {

    private String EMAIL="admin@admin.com";
    private String PASSWORD="admin@admin.com";

    @Override
    public void addCompany(Company company) throws CouponCostumeException {
        if (companyRepository.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CouponCostumeException(ErrMsg.COMPANY_NAME_EMAIL_EXIST);
        }
        company.getCoupons().forEach(t -> t.setCompany(company));
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int id, Company company) throws CouponCostumeException {
        String name=companyRepository.findById(id).get().getName();
        if (!Objects.equals(company.getName(), name)) {
            throw new CouponCostumeException(ErrMsg.COMPANY_NAME_EXIST);
        }
        company.setId(id);
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int id) throws CouponCostumeException {
        if (!companyRepository.existsById(id)){
            throw new CouponCostumeException(ErrMsg.COMPANY_NOT_EXIST);
        }
        couponRepository.deleteAll(companyRepository.findById(id).get().getCoupons());
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponCostumeException {
        return companyRepository.findById(companyId).orElseThrow(()->new CouponCostumeException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponCostumeException {
        if (customerRepository.existsByEmail(customer.getEmail())){
            throw new CouponCostumeException(ErrMsg.CUSTOMER_NAME_EMAIL_EXIST);
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponCostumeException {
        if (customer.getId()!=customerRepository.findById(customerId).get().getId()){
            throw new CouponCostumeException(ErrMsg.CANNOT_UPDATE_ID_CUSTOMER);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int id) throws CouponCostumeException {
        if (!customerRepository.existsById(id)){
            throw new CouponCostumeException(ErrMsg.CUSTOMER_NOT_EXIST);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int id) throws CouponCostumeException {
        return this.customerRepository.findById(id).orElseThrow(()->new CouponCostumeException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public boolean login(String email, String password) throws CouponCostumeException {
        if(!email.equals(EMAIL) || !password.equals(PASSWORD)){
            throw new CouponCostumeException(ErrMsg.EMAIL_OR_PASS_WRONG);
        }
        return true;
    }
}
