package com.onurkus.graduationproject.message.service;

import com.onurkus.graduationproject.credit.converter.CreditMapper;
import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.credit.entity.Credit;
import com.onurkus.graduationproject.customer.converter.CustomerMapper;
import com.onurkus.graduationproject.customer.dto.CustomerSaveDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.service.CustomerService;
import com.onurkus.graduationproject.message.entity.Message;
import com.onurkus.graduationproject.message.repository.MessageRepository;
import com.onurkus.graduationproject.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest extends TestUtils {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private CustomerService customerService;

    @Test
    void sendMessage() {
        Customer customer = generateCustomer(12345678910L);
        Credit credit= generateCredit(customer);

        CreditDto creditDto= CreditMapper.INSTANCE.convertToCreditDto(credit);

        String actual = customer.getPhoneNumber();

        messageService.sendMessage(creditDto);
        String expected = messageRepository.findMessageByIdentityId(creditDto.getIdentityId()).getPhoneNumber();

        assertEquals(expected, actual);

    }

}