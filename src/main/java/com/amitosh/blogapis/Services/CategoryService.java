package com.amitosh.blogapis.Services;

import com.amitosh.blogapis.Dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategory();
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategoryById(CategoryDto categoryDto, Long id);
    void deleteCategoryById(Long id);
}
