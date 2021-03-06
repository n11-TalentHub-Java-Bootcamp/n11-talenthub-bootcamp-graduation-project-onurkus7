package com.onurkus.graduationproject.customer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerDto {

    private Long id;
    private Long identityId;
    private Date registrationDate;
    private String fullName;
    private Date birthdayDate;
    private String phoneNumber;
    private BigDecimal salary;
    private BigDecimal collateral;

}
