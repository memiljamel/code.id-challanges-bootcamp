package com.codeid.eshopay.service.implementation;

import com.codeid.eshopay.model.entity.oe.Category;
import com.codeid.eshopay.model.request.CreateCategoryRequest;
import com.codeid.eshopay.model.request.UpdateCategoryRequest;
import com.codeid.eshopay.model.response.CategoryResponse;
import com.codeid.eshopay.repository.CategoryRepository;
import com.codeid.eshopay.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);

        return categories.getContent().stream()
                .map(CategoryServiceImpl::mapToCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse findById(Short id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));

        return mapToCategoryResponse(category);
    }

    @SneakyThrows
    @Override
    public CategoryResponse save(CreateCategoryRequest request) {
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        if (Objects.nonNull(request.getPicture())) {
            category.setPicture(request.getPicture().getBytes());
        }

        categoryRepository.save(category);

        return mapToCategoryResponse(category);
    }

    @SneakyThrows
    @Override
    public CategoryResponse update(UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + request.getId()));
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        if (Objects.nonNull(request.getPicture())) {
            category.setPicture(request.getPicture().getBytes());
        }

        categoryRepository.save(category);

        return mapToCategoryResponse(category);
    }

    @Override
    public void delete(Short id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));

        categoryRepository.delete(category);
    }

    protected static CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .picture(category.getPicture())
                .createdDate(category.getCreatedDate())
                .modifiedDate(category.getModifiedDate())
                .build();
    }
}
