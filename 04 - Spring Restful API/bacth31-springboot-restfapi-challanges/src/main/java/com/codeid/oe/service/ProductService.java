package com.codeid.oe.service;

import com.codeid.oe.model.dto.ProductDto;
import com.codeid.oe.model.dto.ProductImageDto;
import com.codeid.oe.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService extends BaseCrudService<ProductDto, Short> {

    List<ProductImageDto> bulkFindAll(Short id);

    List<ProductImageDto> bulkCreate(Short id, MultipartFile[] files, List<String> filenames);
}
