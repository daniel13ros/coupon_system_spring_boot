package com.daniel.coupon_system_spring_boot.advice;

import com.daniel.coupon_system_spring_boot.exceptions.CouponCostumeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by danielR on 16/11/2022
 */
@RestControllerAdvice
public class ControllerAdvice {

        @ExceptionHandler(value = {CouponCostumeException.class})
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorDetails handle(Exception e){
            return new ErrorDetails(e.getMessage());
        }
    }


