package com.onurkus.graduationproject.customer.service;

import com.onurkus.graduationproject.customer.converter.CustomerMapper;
import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.dto.CustomerUpdateDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.service.entityservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerEntityService customerEntityService;

    public CustomerDto save(Customer customer)
    {
        customer = customerEntityService.save(customer);
        CustomerDto customerDto = CustomerMapper.INSTANCE.convertToCustomerDto(customer);
        return customerDto;
    }

    public List<CustomerDto> findAllCustomer() {
        List<Customer> customerList = customerEntityService.findAll();
        List<CustomerDto> customerDtoList = CustomerMapper.INSTANCE.convertToCustomerDtoList(customerList);
        return customerDtoList;
    }

    public CustomerDto findByIdentityCustomer(Long identityId) {
        Customer customer = customerEntityService.findByIdentityCustomer(identityId);
        CustomerDto customerDto = CustomerMapper.INSTANCE.convertToCustomerDto(customer);
        return customerDto;
    }

    public CustomerDto updateCustomer(Long identityId,CustomerUpdateDto customerUpdateDto) {

        Customer customer = customerEntityService.findByIdentityCustomer(identityId);

        customer.setPhoneNumber(customerUpdateDto.getPhoneNumber());
        customer.setSalary(customerUpdateDto.getSalary());
        customer.setCollateral(customerUpdateDto.getCollateral());

        CustomerDto customerDto = CustomerMapper.INSTANCE.convertToCustomerDto(customer);
        return customerDto;
    }


    public void deleteCustomer(Long identityId) {
        Customer customer = customerEntityService.findByIdentityCustomer(identityId);
        customerEntityService.delete(customer);
    }
}
