package com.onurkus.graduationproject.customer.controller;

import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.dto.CustomerSaveDto;
import com.onurkus.graduationproject.customer.dto.CustomerUpdateDto;
import com.onurkus.graduationproject.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerSaveDto customerSaveDto) {
        String saveCustomer = customerService.saveCustomer(customerSaveDto);
        return ResponseEntity.ok(saveCustomer);
    }

    @GetMapping
    public List<CustomerDto> findAllCustomer() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/{identityId}")
    public CustomerDto findByIdentityCustomer(@PathVariable Long identityId) {
        return customerService.findByIdentityCustomer(identityId);
    }

    @PutMapping("/{identityId}")
    public CustomerDto updateCustomer(@PathVariable Long identityId, @RequestBody CustomerUpdateDto customerUpdateDto) {
        return customerService.updateCustomer(identityId, customerUpdateDto);
    }

    @DeleteMapping("/{identityId}")
    public void deleteCustomer(@PathVariable Long identityId) {
        customerService.deleteCustomer(identityId);
    }


}
