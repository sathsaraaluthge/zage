package com.zage.zage.service;

import com.zage.zage.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto dto);
    CategoryDto getCategoryById(Integer id);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(Integer id, CategoryDto dto);
    void deleteCategory(Integer id);
}
