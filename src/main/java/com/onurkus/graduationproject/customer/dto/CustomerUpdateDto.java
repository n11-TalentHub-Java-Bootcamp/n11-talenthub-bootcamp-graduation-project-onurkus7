package com.onurkus.graduationproject.customer.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerUpdateDto {

    private String phoneNumber;
    private BigDecimal salary;
    private BigDecimal collateral;

}
