package com.daniel.coupon_system_spring_boot.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by danielR on 16/11/2022
 */
@Data
@AllArgsConstructor
public class ErrorDetails {

    private final String key = "CS 151";
    private String value;
}
