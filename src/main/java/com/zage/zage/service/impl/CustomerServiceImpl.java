package com.zage.zage.service.impl;

import com.zage.zage.dto.CustomerRequestDto;
import com.zage.zage.dto.CustomerResponseDto;
import com.zage.zage.exception.DuplicateException;
import com.zage.zage.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.zage.zage.repository.UserRepository;
import com.zage.zage.dto.User;
import com.zage.zage.repository.CustomerRepository;
import com.zage.zage.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getFirstName() + " " + dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");

        Long userId = userRepository.saveUser(user);
        Long customerId = customerRepository.saveCustomer(userId, dto);

        return customerRepository.findById(customerId).get();
    }

    @Override
    public CustomerResponseDto getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found")
        );
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(Long id) {
        getCustomer(id); // Check existence
        customerRepository.delete(id);
    }
}
