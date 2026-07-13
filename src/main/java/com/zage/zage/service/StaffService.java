package com.zage.zage.service;

import com.zage.zage.dto.StaffRequestDto;
import com.zage.zage.dto.StaffResponseDto;

import java.util.List;

public interface StaffService {
    
    StaffResponseDto createStaff(StaffRequestDto dto);
    
    StaffResponseDto getStaff(Long id);
    
    List<StaffResponseDto> getStaffs();
    
    void deleteStaff(Long id);
}
