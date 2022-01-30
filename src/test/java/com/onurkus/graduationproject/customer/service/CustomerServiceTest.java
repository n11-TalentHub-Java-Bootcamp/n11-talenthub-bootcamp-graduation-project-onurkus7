package com.onurkus.graduationproject.customer.service;

import com.onurkus.graduationproject.customer.converter.CustomerMapper;
import com.onurkus.graduationproject.customer.dto.CustomerSaveDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.repository.CustomerRepository;
import com.onurkus.graduationproject.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest extends TestUtils {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void saveCustomer() {

        Customer customer = generateCustomer(12345678910L);

        CustomerSaveDto customerSaveDto = CustomerMapper.INSTANCE.convertCustomerToCustomerSaveDto(customer);

        String actual = "Customer successfully registered";
        String expected = customerService.saveCustomer(customerSaveDto);

        assertEquals(expected, actual);
    }

    @Test
    void findAllCustomer() {

        List<Customer> actual = new ArrayList<>();
        actual.add(generateCustomer(12345678911L));

        customerRepository.save(actual.get(0));

        List<Customer> expected = customerRepository.findAll();

        Assertions.assertSame(expected, actual);
    }

    @Test
    void findByIdentityCustomer() {

        Customer actual = generateCustomer(12345678912L);

        customerRepository.save(actual);

        Customer expected = customerRepository.findByIdentityId(12345678912L);

        assertEquals(expected, actual);

    }

    @Test
    void updateCustomer() {

        Customer actual = generateCustomer(12345678913L);

        actual.setFullName("Turan Kuş");

        Customer expected = customerRepository.save(actual);

        assertNotEquals(expected, actual);

    }

    @Test
    void deleteCustomer() {

        Customer actual = generateCustomer(12345678914L);

        customerRepository.save(actual);

        customerRepository.delete(actual);

        Customer expected = customerRepository.findByIdentityId(12345678914L);

        assertEquals(expected, null);

    }
}