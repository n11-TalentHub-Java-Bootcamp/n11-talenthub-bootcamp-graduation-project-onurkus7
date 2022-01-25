package com.onurkus.graduationproject.credit.service;

import com.onurkus.graduationproject.credit.converter.CreditMapper;
import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.credit.entity.Credit;
import com.onurkus.graduationproject.credit.enums.EnumCreditStatus;
import com.onurkus.graduationproject.credit.service.entityservice.CreditEntityService;
import com.onurkus.graduationproject.customer.converter.CustomerMapper;
import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.service.entityservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditEntityService creditEntityService;
    private final CustomerEntityService customerEntityService;

    private int tempCS=450;
    private int salary=0;

    private final int CREDIT_LIMIT_MULTIPLIER = 4;

    private BigDecimal limit;
    private BigDecimal rate;

    public CreditDto findCreditLimitByIdentityId(Long identityId){
        Credit credit=creditEntityService.findCreditLimitByIdentityId(identityId);

        return CreditMapper.INSTANCE.convertToCreditDto(credit);
    }

    public CreditDto getCreditLimitByIdentityId(Long identityId){

        CustomerDto customerDto= getCustomerByIdentityId(identityId);
        CreditDto creditDto = null;
        creditDto.setCustomerId(identityId);
        creditDto.setCreditScore(tempCS);
        salary=customerDto.getSalary().intValue();
                
        if(tempCS<500){

            creditDto.setCreditStatus(EnumCreditStatus.REJECTED);
            creditDto.setCreditLimit(ZERO);
        }

        else if(tempCS<1000){

            if(salary<5000){

                limit=BigDecimal.valueOf(10000);
                rate=BigDecimal.valueOf(10);
                creditDto.setCreditStatus(EnumCreditStatus.APPROVED);
                creditDto.setCreditLimit(calculateCreditLimit(limit,customerDto.getCollateral(),rate));
            }

            else if(salary<10000){

                limit=BigDecimal.valueOf(20000);
                rate=BigDecimal.valueOf(20);
                creditDto.setCreditStatus(EnumCreditStatus.APPROVED);
                creditDto.setCreditLimit(calculateCreditLimit(limit,customerDto.getCollateral(),rate));

            }

            else{

                limit=customerDto.getSalary().multiply(BigDecimal.valueOf(CREDIT_LIMIT_MULTIPLIER/2));
                rate=BigDecimal.valueOf(25);
                creditDto.setCreditStatus(EnumCreditStatus.APPROVED);
                creditDto.setCreditLimit(calculateCreditLimit(limit,customerDto.getCollateral(),rate));

            }

        }

        else{

            limit=customerDto.getSalary().multiply(BigDecimal.valueOf(CREDIT_LIMIT_MULTIPLIER));
            rate=BigDecimal.valueOf(50);
            creditDto.setCreditStatus(EnumCreditStatus.APPROVED);
            creditDto.setCreditLimit(calculateCreditLimit(limit,customerDto.getCollateral(),rate));

        }

        saveCredit(creditDto);

        return creditDto;
    }

    public CustomerDto getCustomerByIdentityId(Long identityId){
        Customer customer=customerEntityService.findByIdentityCustomer(identityId);

        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public BigDecimal calculateCreditLimit(BigDecimal limit, BigDecimal collateral,BigDecimal rate){
        return limit.add(collateral.multiply(rate).divide(BigDecimal.valueOf(100)));
    }

    public void saveCredit(CreditDto creditDto){
        creditEntityService.save(CreditMapper.INSTANCE.convertToCredit(creditDto));
    }

}
