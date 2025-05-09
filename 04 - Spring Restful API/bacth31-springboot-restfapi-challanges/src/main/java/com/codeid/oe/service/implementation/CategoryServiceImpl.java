package com.codeid.oe.service.implementation;

import com.codeid.oe.model.dto.CategoryDto;
import com.codeid.oe.model.entity.Category;
import com.codeid.oe.repository.CategoryRepository;
import com.codeid.oe.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        log.debug("request fetching data category");

        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Short id) {
        log.debug("Request to get category : {}", id);

        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));

        return mapToDto(category);
    }

    @Override
    public CategoryDto save(CategoryDto entity) {
        log.debug("Request to create category : {}", entity);

        Category category = new Category();
        category.setCategoryName(entity.getCategoryName());
        category.setDescription(entity.getDescription());
        this.categoryRepository.save(category);

        return mapToDto(category);
    }

    @Override
    public CategoryDto update(Short id, CategoryDto entity) {
        log.debug("Request to update category : {}", id);

        var category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
        category.setCategoryName(entity.getCategoryName());
        category.setDescription(entity.getDescription());
        this.categoryRepository.save(category);

        return mapToDto(category);
    }

    @Override
    public void delete(Short id) {
        log.debug("Request to delete category : {}", id);

        var category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find category with id " + id));

        this.categoryRepository.delete(category);
    }

    public static CategoryDto mapToDto(Category category) {
        return new CategoryDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription(),
                category.getPicture()
        );
    }
}
