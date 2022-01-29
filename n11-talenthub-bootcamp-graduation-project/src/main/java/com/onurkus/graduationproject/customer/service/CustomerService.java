package com.onurkus.graduationproject.customer.service;

import com.onurkus.graduationproject.customer.converter.CustomerMapper;
import com.onurkus.graduationproject.customer.dao.CustomerDao;
import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.dto.CustomerSaveDto;
import com.onurkus.graduationproject.customer.dto.CustomerUpdateDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDao customerDao;

    public String saveCustomer(CustomerSaveDto customerSaveDto) {
        final Long identityId = customerSaveDto.getIdentityId();

        if (String.valueOf(identityId).length() != 11) {
            return "Identity is not failed";
        }

        Customer customer = CustomerMapper.INSTANCE.convertCustomerSaveDtoToCustomer(customerSaveDto);
        customerDao.save(customer);
        return "Customer successfully registered";
    }

    public List<CustomerDto> findAllCustomer() {
        List<Customer> customerList = customerDao.findAll();

        return CustomerMapper.INSTANCE.convertToCustomerDtoList(customerList);
    }

    public CustomerDto findByIdentityCustomer(Long identityId) {

        Customer customer = customerDao.findByIdentityId(identityId);

        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public CustomerDto updateCustomer(Long identityId, CustomerUpdateDto customerUpdateDto) {

        Customer customer = customerDao.findByIdentityId(identityId);

        customer.setPhoneNumber(customerUpdateDto.getPhoneNumber());
        customer.setSalary(customerUpdateDto.getSalary());
        customer.setCollateral(customerUpdateDto.getCollateral());
        customerDao.save(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public void deleteCustomer(Long identityId) {
        Customer customer = customerDao.findByIdentityId(identityId);
        customerDao.delete(customer);
    }

    public String findPhoneNumberByIdentityId(Long identityId) {
        return customerDao.findPhoneNumberByIdentityId(identityId);
    }


}
