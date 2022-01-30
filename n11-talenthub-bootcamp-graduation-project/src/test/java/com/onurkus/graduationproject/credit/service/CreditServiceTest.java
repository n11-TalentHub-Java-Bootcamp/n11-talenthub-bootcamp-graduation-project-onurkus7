package com.onurkus.graduationproject.credit.service;

import com.onurkus.graduationproject.credit.converter.CreditMapper;
import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.credit.entity.Credit;
import com.onurkus.graduationproject.credit.repository.CreditRepository;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.repository.CustomerRepository;
import com.onurkus.graduationproject.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest extends TestUtils {

    @InjectMocks
    private CreditService creditService;

    @Mock
    private CreditRepository creditRepository;

    @Mock
    private CustomerRepository customerRepository;


    @Test
    void getCreditLimitByIdentityIdAndBirthdayDate() {

        Customer customer = generateCustomer(12345678910L);
        Credit credit = generateCredit(customer);

        customerRepository.save(customer);
        creditRepository.save(credit);

        CreditDto actual = CreditMapper.INSTANCE.convertToCreditDto(credit);

        CreditDto expected = creditService.getCreditLimitByIdentityIdAndBirthdayDate(credit.getIdentityId(),
                customer.getBirthdayDate());


        assertEquals(expected, actual);
        
    }

    @Test
    void appCreditLimitByIdentityId() {

        Customer customer = generateCustomer(12345678910L);
        Credit credit = generateCredit(customer);

        CreditDto actual = CreditMapper.INSTANCE.convertToCreditDto(credit);

        CreditDto expected = creditService.appCreditLimitByIdentityId(12345678910L);

        assertEquals(expected, actual);

    }
}