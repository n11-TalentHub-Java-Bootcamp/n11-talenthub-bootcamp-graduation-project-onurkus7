package com.onurkus.graduationproject.customer.converter;

import com.onurkus.graduationproject.customer.dto.CustomerDto;
import com.onurkus.graduationproject.customer.dto.CustomerSaveDto;
import com.onurkus.graduationproject.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    CustomerDto convertToCustomerDto(Customer customer);

    Customer convertToCustomer(CustomerDto customerDto);

    Customer convertCustomerSaveDtoToCustomer(CustomerSaveDto customerSaveDto);

    List<CustomerDto> convertToCustomerDtoList(List<Customer> customer);

}
