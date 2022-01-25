package com.onurkus.graduationproject.customer.service;

import com.onurkus.graduationproject.customer.converter.CustomerMapper;
import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.dto.CustomerSaveDto;
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

    public CustomerDto saveCustomer(CustomerSaveDto customerSaveDto)
    {
        Customer customer = CustomerMapper.INSTANCE.convertCustomerSaveDtoToCustomer(customerSaveDto);
        customer = customerEntityService.save(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public List<CustomerDto> findAllCustomer() {
        List<Customer> customerList = customerEntityService.findAll();

        return CustomerMapper.INSTANCE.convertToCustomerDtoList(customerList);
    }

    public CustomerDto findByIdentityCustomer(Long identityId) {
        Customer customer = customerEntityService.findByIdentityCustomer(identityId);

        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public CustomerDto updateCustomer(Long identityId,CustomerUpdateDto customerUpdateDto) {

        Customer customer = customerEntityService.findByIdentityCustomer(identityId);

        customer.setPhoneNumber(customerUpdateDto.getPhoneNumber());
        customer.setSalary(customerUpdateDto.getSalary());
        customer.setCollateral(customerUpdateDto.getCollateral());
        customerEntityService.save(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }


    public void deleteCustomer(Long identityId) {
        Customer customer = customerEntityService.findByIdentityCustomer(identityId);
        customerEntityService.delete(customer);
    }
}
