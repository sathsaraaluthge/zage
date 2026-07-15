package com.zage.zage.service.impl;

import com.zage.zage.dto.CategoryDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.CategoryRepository;
import com.zage.zage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        return categoryRepository.insertCategory(dto);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
        );
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryDto updateCategory(Integer id, CategoryDto dto) {
        // Optional: Check if category exists before update
        getCategoryById(id);
        return categoryRepository.updateCategory(id, dto);
    }

    @Override
    public void deleteCategory(Integer id) {
        // Optional: Check if category exists before delete
        getCategoryById(id);
        categoryRepository.deleteCategory(id);
    }
}
