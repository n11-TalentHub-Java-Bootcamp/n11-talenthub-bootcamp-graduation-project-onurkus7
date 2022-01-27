package com.onurkus.graduationproject.credit.dto;

import com.onurkus.graduationproject.credit.enums.EnumCreditStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditDto {

    private Long customerId;
    private Long identityId;
    private Integer creditScore;
    private EnumCreditStatus creditStatus;
    private BigDecimal creditLimit;

}
