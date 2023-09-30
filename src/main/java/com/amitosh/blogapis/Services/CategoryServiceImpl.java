package com.amitosh.blogapis.Services;

import com.amitosh.blogapis.Dtos.CategoryDto;
import com.amitosh.blogapis.Enitities.Category;
import com.amitosh.blogapis.Repositories.CategoryRepository;
import com.amitosh.blogapis.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("category", "id", id));
        return CategoryToCategoryDto(category);

    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(CategoryToCategoryDto(category));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.categoryRepository.save(CategoryDtoTocategory(categoryDto));
        return CategoryToCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto categoryDto, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("category", "id", id));

        category.setId(categoryDto.getId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category savedCategory = categoryRepository.save(category);
        return CategoryToCategoryDto(savedCategory);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        categoryRepository.deleteById(id);
    }

    Category CategoryDtoTocategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        return category;
    }
    CategoryDto CategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());
        return categoryDto;
    }
}
