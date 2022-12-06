package com.daniel.coupon_system_spring_boot.jobs;

import com.daniel.coupon_system_spring_boot.beans.Coupon;
import com.daniel.coupon_system_spring_boot.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by danielR on 16/11/2022
 */
@Component

public class DailyRemoveExpiredCoupons {
    @Autowired
    private CouponRepository couponRepository;

    @Scheduled(fixedRate = 86_400_000)

    public void initRemover() {

//        List<Coupon> toDelete = couponRepository.findByEndDateBefore(Date.valueOf(LocalDate.now()));
//        couponRepository.deleteAll(toDelete);
        couponRepository.deleteExpired(Date.valueOf(LocalDate.now()));
        couponRepository.deleteExpiredPurchase();
        System.out.println("Deleted expired coupons");
    }

}
