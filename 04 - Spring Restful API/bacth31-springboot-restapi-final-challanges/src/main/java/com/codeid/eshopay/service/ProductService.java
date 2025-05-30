package com.codeid.eshopay.service;

import com.codeid.eshopay.model.request.BulkProductImageRequest;
import com.codeid.eshopay.model.request.CreateProductRequest;
import com.codeid.eshopay.model.request.UpdateProductRequest;
import com.codeid.eshopay.model.response.ProductImageResponse;
import com.codeid.eshopay.model.response.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends BaseCrudService<
        CreateProductRequest,
        UpdateProductRequest,
        ProductResponse,
        Short> {

    List<ProductResponse> findAllWithCategory(String keyword, Pageable pageable);

    List<ProductImageResponse> bulkInsert(BulkProductImageRequest request);

    void delete(Short productId, Short imageId);
}
