package com.codeid.eshopay.service;

import com.codeid.eshopay.model.request.CreateCategoryRequest;
import com.codeid.eshopay.model.request.UpdateCategoryRequest;
import com.codeid.eshopay.model.response.CategoryResponse;

public interface CategoryService extends BaseCrudService<
        CreateCategoryRequest,
        UpdateCategoryRequest,
        CategoryResponse,
        Short> {
}
