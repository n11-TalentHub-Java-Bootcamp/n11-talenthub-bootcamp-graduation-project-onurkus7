package com.onurkus.graduationproject.message.service;

import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.service.CustomerService;
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
    void sendMessageByIdentityId() {
        Customer customer = generateCustomer(12345678910L);

        String actual = customer.getPhoneNumber();

        String expected = customerService.findPhoneNumberByIdentityId(customer.getIdentityId());

        assertEquals(expected, actual);

    }

}