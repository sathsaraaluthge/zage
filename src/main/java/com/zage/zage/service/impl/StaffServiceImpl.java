package com.zage.zage.service.impl;

import com.zage.zage.dto.StaffRequestDto;
import com.zage.zage.dto.StaffResponseDto;
import com.zage.zage.exception.DuplicateException;
import com.zage.zage.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.zage.zage.repository.StaffRepository;
import com.zage.zage.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public StaffResponseDto createStaff(StaffRequestDto dto) {
        if (staffRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateException("Email already exists");
        }
        
        if (staffRepository.existsByStaffname(dto.getStaffname())) {
            throw new DuplicateException("Staffname already exists");
        }

        // Encode password before saving
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Long staffId = staffRepository.saveStaff(dto);

        return staffRepository.findById(staffId).get();
    }

    @Override
    public StaffResponseDto getStaff(Long id) {
        return staffRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Staff not found")
        );
    }

    @Override
    public List<StaffResponseDto> getStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public void deleteStaff(Long id) {
        getStaff(id);
        staffRepository.delete(id);
    }
}