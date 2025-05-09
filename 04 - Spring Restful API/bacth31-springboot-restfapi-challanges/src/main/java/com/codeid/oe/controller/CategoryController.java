package com.codeid.oe.controller;

import com.codeid.oe.model.dto.CategoryDto;
import com.codeid.oe.service.BaseCrudService;
import com.codeid.oe.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryController extends BaseCrudController<CategoryDto, Short> {

    private final CategoryService categoryService;

    @Override
    protected BaseCrudService<CategoryDto, Short> getService() {
        return categoryService;
    }
}
