package com.zage.zage.service;

import com.zage.zage.dto.CustomerRequestDto;
import com.zage.zage.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto dto);
    CustomerResponseDto getCustomer(Long id);
    List<CustomerResponseDto> getAllCustomers();
    void deleteCustomer(Long id);
}
