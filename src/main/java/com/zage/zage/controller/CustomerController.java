package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.CustomerRequestDto;
import com.zage.zage.dto.CustomerResponseDto;
import com.zage.zage.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> createCustomer(@RequestBody CustomerRequestDto dto) {
        CustomerResponseDto createdCustomer = customerService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Customer created successfully", createdCustomer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getCustomer(@PathVariable Long id) {
        CustomerResponseDto customer = customerService.getCustomer(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customer fetched successfully", customer));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> getCustomers() {
        List<CustomerResponseDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customers fetched successfully", customers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customer deleted successfully", null));
    }
}
