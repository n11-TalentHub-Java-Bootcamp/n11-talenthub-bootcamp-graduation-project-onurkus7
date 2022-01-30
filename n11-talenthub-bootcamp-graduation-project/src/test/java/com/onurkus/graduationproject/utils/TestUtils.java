package com.onurkus.graduationproject.utils;

import com.onurkus.graduationproject.credit.entity.Credit;
import com.onurkus.graduationproject.credit.enums.EnumCreditStatus;
import com.onurkus.graduationproject.customer.entity.Customer;

import java.math.BigDecimal;
import java.util.Date;

public class TestUtils {

    public Customer generateCustomer(Long identityId) {

        Customer customer = new Customer();
        customer.setId(identityId);
        customer.setIdentityId(identityId);
        customer.setRegistrationDate(new Date());
        customer.setFullName("Onur Kuş");
        customer.setBirthdayDate(new Date());
        customer.setPhoneNumber("+905998887755");
        customer.setSalary(BigDecimal.valueOf(5800L));
        customer.setCollateral(BigDecimal.valueOf(58000L));

        return customer;
    }

    public Credit generateCredit(Customer customer) {

        Credit credit = new Credit();
        credit.setId(999L);
        credit.setCustomerId(customer);
        credit.setIdentityId(12345678910L);
        credit.setCreditScore(1000);
        credit.setCreditStatus(EnumCreditStatus.APPROVED);
        credit.setCreditLimit(BigDecimal.valueOf(58000));

        return credit;
    }

}


