package com.daniel.coupon_system_spring_boot.repos;

import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by danielR on 15/11/2022
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    Customer findByEmailAndPassword(String email, String password);
}
