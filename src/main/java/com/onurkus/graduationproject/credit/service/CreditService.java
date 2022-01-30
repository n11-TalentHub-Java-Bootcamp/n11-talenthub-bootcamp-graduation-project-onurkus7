package com.onurkus.graduationproject.credit.service;

import com.onurkus.graduationproject.credit.converter.CreditMapper;
import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.credit.enums.EnumCreditStatus;
import com.onurkus.graduationproject.credit.repository.CreditRepository;
import com.onurkus.graduationproject.customer.converter.CustomerMapper;
import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.repository.CustomerRepository;
import com.onurkus.graduationproject.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final CustomerRepository customerRepository;
    private final MessageService messageService;

    private static final Logger LOGGER = LogManager.getLogger(CreditService.class);

    private static final Integer CREDIT_LIMIT_MULTIPLIER = 4;

    public CreditDto getCreditLimitByIdentityIdAndBirthdayDate(
            Long identityId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdayDate) {

        LOGGER.info("The credit information of the customer with the birth date of " +
                birthdayDate + " and id " + identityId + " is returned");
        return CreditMapper.INSTANCE.convertToCreditDto(
                creditRepository.findCreditByIdentityIdAndBirthdayDate(identityId, birthdayDate).get(0));
    }

    public CreditDto appCreditLimitByIdentityId(Long identityId) {

        CustomerDto customerDto = getCustomerByIdentityId(identityId);

        CreditDto creditDto = populateCreditDto(customerDto);

        saveCredit(creditDto);

        LOGGER.info("Calculation processes were started as a result of the credit limit of the customer with " +
                identityId + " ID.");
        return creditDto;
    }

    private CustomerDto getCustomerByIdentityId(Long identityId) {
        Customer customer = customerRepository.findByIdentityId(identityId);

        LOGGER.info("The information of the customer with " + identityId + " id has been taken");
        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    private CreditDto populateCreditDto(CustomerDto customerDto) {

        CreditDto creditDto = new CreditDto();

        int customerSalary;

        BigDecimal creditLimit;
        BigDecimal collateralRate;

        creditDto.setCustomerId(customerDto.getId());
        creditDto.setIdentityId(customerDto.getIdentityId());
        customerSalary = customerDto.getSalary().intValue();

        int temporalCreditScore = createRandomCreditScore(customerSalary);
        creditDto.setCreditScore(temporalCreditScore);

        if (temporalCreditScore < 500) {

            creditDto.setCreditStatus(EnumCreditStatus.REJECTED);
            creditDto.setCreditLimit(ZERO);
        } else if (temporalCreditScore < 1000) {

            if (customerSalary < 5000) {

                creditLimit = BigDecimal.valueOf(10000);
                collateralRate = BigDecimal.valueOf(10);
                creditDto.setCreditStatus(EnumCreditStatus.APPROVED);
                creditDto.setCreditLimit(calculateCreditLimit(creditLimit, customerDto.getCollateral(), collateralRate));
            } else if (customerSalary < 10000) {

                creditLimit = BigDecimal.valueOf(20000);
                collateralRate = BigDecimal.valueOf(20);
                creditDto.setCreditStatus(EnumCreditStatus.APPROVED);
                creditDto.setCreditLimit(calculateCreditLimit(creditLimit, customerDto.getCollateral(), collateralRate));

            } else {

                creditLimit = customerDto.getSalary().multiply(BigDecimal.valueOf(CREDIT_LIMIT_MULTIPLIER / 2));
                collateralRate = BigDecimal.valueOf(25);
                creditDto.setCreditStatus(EnumCreditStatus.APPROVED);
                creditDto.setCreditLimit(calculateCreditLimit(creditLimit, customerDto.getCollateral(), collateralRate));

            }

        } else {

            creditLimit = customerDto.getSalary().multiply(BigDecimal.valueOf(CREDIT_LIMIT_MULTIPLIER));
            collateralRate = BigDecimal.valueOf(50);
            creditDto.setCreditStatus(EnumCreditStatus.APPROVED);
            creditDto.setCreditLimit(calculateCreditLimit(creditLimit, customerDto.getCollateral(), collateralRate));

        }

        LOGGER.info("The information of the customer with id " + creditDto.getIdentityId() +
                " and the credit limit result are matched to the creditDto object.");
        return creditDto;
    }

    private Integer createRandomCreditScore(int customerSalary) {
        Random random = new Random();

        int value1 = random.nextInt(13) + 1;
        int value2 = customerSalary / 70;

        LOGGER.info("Random credit score of the customer was generated.");
        return value1 * value2;
    }

    private BigDecimal calculateCreditLimit(BigDecimal creditLimit, BigDecimal collateral, BigDecimal collateralRate) {

        LOGGER.info("Credit limit calculations were made.");
        return creditLimit.add(collateral.multiply(collateralRate).divide(BigDecimal.valueOf(100)));
    }

    private void saveCredit(CreditDto creditDto) {
        creditRepository.save(CreditMapper.INSTANCE.convertToCredit(creditDto));
        messageService.sendMessage(creditDto);

        LOGGER.info("The credit limit result of the customer with " + creditDto.getIdentityId() +
                " id has been saved in the database.");

    }

}
