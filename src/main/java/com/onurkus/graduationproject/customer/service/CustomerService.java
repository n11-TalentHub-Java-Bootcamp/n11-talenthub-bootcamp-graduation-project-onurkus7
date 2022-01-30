package com.onurkus.graduationproject.customer.service;

import com.onurkus.graduationproject.credit.repository.CreditRepository;
import com.onurkus.graduationproject.customer.converter.CustomerMapper;
import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.dto.CustomerSaveDto;
import com.onurkus.graduationproject.customer.dto.CustomerUpdateDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.repository.CustomerRepository;
import com.onurkus.graduationproject.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CreditRepository creditRepository;
    private final MessageRepository messageRepository;

    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);


    public String saveCustomer(CustomerSaveDto customerSaveDto) {

        final Long identityId = customerSaveDto.getIdentityId();

        if (String.valueOf(identityId).length() != 11) {

            LOGGER.error("Identity is failed.");
            return "Identity is failed";
        }

        Customer customer = CustomerMapper.INSTANCE.convertCustomerSaveDtoToCustomer(customerSaveDto);
        customerRepository.save(customer);

        LOGGER.info(customerSaveDto.getFullName() + " is saved.");
        return "Customer successfully registered";
    }

    public List<CustomerDto> findAllCustomer() {

        List<Customer> customerList = customerRepository.findAll();

        LOGGER.info("Customer service's findAllCustomer method is running.");
        return CustomerMapper.INSTANCE.convertToCustomerDtoList(customerList);
    }

    public CustomerDto findByIdentityCustomer(Long identityId) {

        Customer customer = customerRepository.findByIdentityId(identityId);

        LOGGER.info("Customer information with " + identityId + " is returned");
        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public CustomerDto updateCustomer(Long identityId, CustomerUpdateDto customerUpdateDto) {

        Customer customer = customerRepository.findByIdentityId(identityId);

        customer.setPhoneNumber(customerUpdateDto.getPhoneNumber());
        customer.setSalary(customerUpdateDto.getSalary());
        customer.setCollateral(customerUpdateDto.getCollateral());
        customerRepository.save(customer);

        LOGGER.info("Customer information with " + identityId + " is updated");
        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public void deleteCustomer(Long identityId) {
        Customer customer = customerRepository.findByIdentityId(identityId);

        creditRepository.deleteCreditByIdentityId(identityId);
        messageRepository.deleteMessageByIdentityId(identityId);
        customerRepository.delete(customer);


        LOGGER.info("Customer information with " + identityId + " is deleted");
    }

    public String findPhoneNumberByIdentityId(Long identityId) {

        LOGGER.info("The phone information of the customer with " + identityId + " is returned");
        return customerRepository.findPhoneNumberByIdentityId(identityId);
    }


}
