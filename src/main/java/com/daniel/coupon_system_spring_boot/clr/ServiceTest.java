package com.daniel.coupon_system_spring_boot.clr;

import com.daniel.coupon_system_spring_boot.beans.*;
import com.daniel.coupon_system_spring_boot.login.LoginManager;
import com.daniel.coupon_system_spring_boot.services.*;
import com.daniel.coupon_system_spring_boot.util.BeautifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by danielR on 15/11/2022
 */
@Component
@Order(1)
public class ServiceTest implements CommandLineRunner {

    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        BeautifyUtil.startOrEnd("THIS IS SERVICE TEST");

        Company company1 = Company.builder()
                .name("company1")
                .email("company1@company1.com")
                .password("1234")
                .build();
        Company company2 = Company.builder()
                .name("company2")
                .email("company2@company2.com")
                .password("1234")
                .build();
        Company company3 = Company.builder()
                .name("company3")
                .email("company3@company3.com")
                .password("1234")
                .build();
        Company company4 = Company.builder()
                .name("company4")
                .email("company4@company4.com")
                .password("1234")
                .build();
        Company company5 = Company.builder()
                .name("company5")
                .email("company5@company5.com")
                .password("1234")
                .build();

        Customer customer1 = Customer.builder()
                .firstName("customer1")
                .lastName("cus1")
                .email("customer1@cus1.com")
                .password("1234")
                .build();
        Customer customer2 = Customer.builder()
                .firstName("customer2")
                .lastName("cus2")
                .email("customer2@cus2.com")
                .password("1234")
                .build();
        Customer customer3 = Customer.builder()
                .firstName("customer3")
                .lastName("cus3")
                .email("customer3@cus3.com")
                .password("1234")
                .build();
        Customer customer4 = Customer.builder()
                .firstName("customer4")
                .lastName("cus4")
                .email("customer4@cus4.com")
                .password("1234")
                .build();
        Customer customer5 = Customer.builder()
                .firstName("customer5")
                .lastName("cus5")
                .email("customer5@cus5.com")
                .password("1234")
                .build();


        Coupon coupon1 = Coupon.builder()
                .company(company1)
                .category(Category.Electronics)
                .title("title1")
                .description("desc1")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(15)))
                .amount(4)
                .price(54)
                .image("aaa")
                .build();
        Coupon coupon2 = Coupon.builder()
                .company(company1)
                .category(Category.Electronics)
                .title("title2")
                .description("desc2")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(15)))
                .amount(6)
                .price(124)
                .image("bbb")
                .build();
        Coupon coupon3 = Coupon.builder()
                .company(company1)
                .category(Category.Gifts)
                .title("title3")
                .description("desc3")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(15)))
                .amount(12)
                .price(159)
                .image("ccc")
                .build();
        Coupon coupon4 = Coupon.builder()
                .company(company1)
                .category(Category.Restaurants)
                .title("title4")
                .description("desc4")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(66)))
                .amount(10)
                .price(320)
                .image("ddd")
                .build();
        Coupon coupon5 = Coupon.builder()
                .company(company1)
                .category(Category.Shoes)
                .title("title5")
                .description("desc5")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(26)))
                .amount(8)
                .price(269)
                .image("eee")
                .build();
        Coupon coupon6 = Coupon.builder()
                .company(company1)
                .category(Category.Shoes)
                .title("title6")
                .description("desc6")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .amount(8)
                .price(269)
                .image("eee")
                .build();

        //Admin functionality
        AdminService adminService1 = (AdminService) loginManager.login("admin@admin.com", "admin@admin.com", ClientType.Administrator);

        adminService1.addCompany(company1);
        adminService1.addCompany(company2);
        adminService1.addCompany(company3);
        adminService1.addCompany(company4);
        adminService1.addCompany(company5);

        company1.setEmail("company@company.com");

        adminService1.updateCompany(1, company1);

        adminService1.deleteCompany(5);


        BeautifyUtil.title( "All companies" );
        adminService1.getAllCompanies().forEach(System.out::println);

        BeautifyUtil.title( "company 1" );
        System.out.println(adminService1.getSingleCompany(1).toString());

        adminService1.addCustomer(customer1);
        adminService1.addCustomer(customer2);
        adminService1.addCustomer(customer3);
        adminService1.addCustomer(customer4);
        adminService1.addCustomer(customer5);

        customer1.setFirstName("customer");
        adminService1.updateCustomer(1, customer1);

        adminService1.deleteCustomer(5);

        BeautifyUtil.title( "All customers" );
        adminService1.getAllCustomers().forEach(System.out::println);

        BeautifyUtil.title( "customer 1" );
        System.out.println(adminService1.getSingleCustomer(1));

        //Company functionality
        CompanyService companyService1 = (CompanyService) loginManager.login("company@company.com", "1234", ClientType.Company);

        companyService1.addCoupon(company1.getId(),coupon1);
        companyService1.addCoupon(company1.getId(),coupon2);
        companyService1.addCoupon(company1.getId(),coupon3);
        companyService1.addCoupon(company1.getId(),coupon4);
        companyService1.addCoupon(company1.getId(),coupon5);
        companyService1.addCoupon(company1.getId(),coupon6);

        coupon1.setCategory(Category.Gifts);
        companyService1.updateCoupon(1, coupon1 , 1);

        companyService1.deleteCoupon(5);

        BeautifyUtil.title( "company 1 coupons" );
        companyService1.getCompanyCoupons(company1.getId()).forEach(System.out::println);

        BeautifyUtil.title( "company 1 coupons - electronics" );
        companyService1.getCompanyCoupons(company1.getId(),Category.Electronics).forEach(System.out::println);

        BeautifyUtil.title( "company 1 all coupons that price under 300" );
        companyService1.getCompanyCoupons(company1.getId(),300).forEach(System.out::println);

        BeautifyUtil.title( "company 1 details");
        companyService1.getCompanyDetails(company1.getId());

        //Customer functionality
        CustomerService customerService1 = (CustomerService) loginManager.login("customer2@cus2.com", "1234", ClientType.Customer);

        customerService1.purchaseCoupon(customer2.getId(),coupon1,coupon1.getId());
        customerService1.purchaseCoupon(customer2.getId(),coupon2,coupon2.getId());
        customerService1.purchaseCoupon(customer2.getId(),coupon3,coupon3.getId());
        customerService1.purchaseCoupon(customer2.getId(),coupon4,coupon4.getId());
        customerService1.purchaseCoupon(customer2.getId(),coupon6,coupon6.getId());

        BeautifyUtil.title( "customer 2 all coupons:");
        customerService1.getCustomerCoupons(customer2.getId()).forEach(System.out::println);

        BeautifyUtil.title( "customer 2 all coupons by category:");
        customerService1.getCustomerCoupons(customer2.getId(),Category.Electronics).forEach(System.out::println);

        BeautifyUtil.title( "customer 2 all coupons that price under 300");
        customerService1.getCustomerCoupons(customer2.getId(),245).forEach(System.out::println);

        BeautifyUtil.title( "customer 2 details");
        customerService1.getCustomerDetails(customer2.getId());


    }
}
