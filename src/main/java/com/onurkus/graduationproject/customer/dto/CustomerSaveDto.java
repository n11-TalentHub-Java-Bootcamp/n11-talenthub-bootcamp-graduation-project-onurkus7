package com.onurkus.graduationproject.customer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerSaveDto {

    private Long identityId;
    private String fullName;
    private Date birthdayDate;
    private String phoneNumber;
    private BigDecimal salary;
    private BigDecimal collateral;

}
