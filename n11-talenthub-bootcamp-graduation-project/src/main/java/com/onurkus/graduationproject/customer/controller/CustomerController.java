package com.onurkus.graduationproject.customer.controller;

import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.dto.CustomerSaveDto;
import com.onurkus.graduationproject.customer.dto.CustomerUpdateDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/app/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("/customer-save")
    public CustomerDto saveCustomer(@RequestBody CustomerSaveDto customerSaveDto)
    {
        return customerService.saveCustomer(customerSaveDto);
    }

    @GetMapping("/customer-search")
    public List<CustomerDto> findAllCustomer() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/customer-search/{identityId}")
    public CustomerDto findByIdentityCustomer(@PathVariable Long identityId) {
        return customerService.findByIdentityCustomer(identityId);
    }

    @PutMapping("/customer-update/{identityId}")
    public CustomerDto updateCustomer(@PathVariable Long identityId, @RequestBody CustomerUpdateDto customerUpdateDto) {
        return customerService.updateCustomer(identityId,customerUpdateDto);
    }

    @DeleteMapping("/customer-delete/{identityId}")
    public void deleteCustomer(@PathVariable Long identityId) {
        customerService.deleteCustomer(identityId);
    }



}
