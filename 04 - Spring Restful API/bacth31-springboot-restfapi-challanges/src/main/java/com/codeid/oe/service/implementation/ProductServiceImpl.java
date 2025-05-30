package com.codeid.oe.service.implementation;

import com.codeid.oe.model.dto.ProductDto;
import com.codeid.oe.model.dto.ProductImageDto;
import com.codeid.oe.model.entity.Category;
import com.codeid.oe.model.entity.Product;
import com.codeid.oe.model.entity.ProductImage;
import com.codeid.oe.model.entity.Supplier;
import com.codeid.oe.repository.CategoryRepository;
import com.codeid.oe.repository.ProductImageRepository;
import com.codeid.oe.repository.ProductRepository;
import com.codeid.oe.repository.SupplierRepository;
import com.codeid.oe.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final FileStorageService fileStorageService;

    @Override
    public List<ProductDto> findAll() {
        log.debug("request fetching data product");

        return this.productRepository.findAll()
                .stream()
                .map(ProductServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Short id) {
        log.debug("Request to get product : {}", id);

        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id));

        return mapToDto(product);
    }

    @Override
    public ProductDto save(ProductDto entity) {
        log.debug("Request to create product : {}", entity);

        Supplier supplier = this.supplierRepository.findById(entity.getSupplierId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id " + entity.getSupplierId()));

        Category category = this.categoryRepository.findById(entity.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id " + entity.getCategoryId()));

        Product product = new Product();
        product.setProductName(entity.getProductName());
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(entity.getQuantityPerUnit());
        product.setUnitPrice(entity.getUnitPrice());
        product.setUnitsInStock(entity.getUnitsInStock());
        product.setUnitsInOrder(entity.getUnitsInOrder());
        product.setReorderLevel(entity.getReorderLevel());
        product.setDiscontinued(entity.getDiscontinued());
        product.setPicture(entity.getPicture());

        return mapToDto(this.productRepository.save(product));
    }

    @Override
    public ProductDto update(Short id, ProductDto entity) {
        log.debug("Request to update product : {}", id);

        Supplier supplier = this.supplierRepository.findById(entity.getSupplierId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id " + entity.getSupplierId()));

        Category category = this.categoryRepository.findById(entity.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id " + entity.getCategoryId()));

        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id));
        product.setProductName(entity.getProductName());
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(entity.getQuantityPerUnit());
        product.setUnitPrice(entity.getUnitPrice());
        product.setUnitsInStock(entity.getUnitsInStock());
        product.setUnitsInOrder(entity.getUnitsInOrder());
        product.setReorderLevel(entity.getReorderLevel());
        product.setDiscontinued(entity.getDiscontinued());

        if (Objects.nonNull(product.getPicture())) {
            this.fileStorageService.delete(product.getPicture());

            product.setPicture(entity.getPicture());
        }

        return mapToDto(this.productRepository.save(product));
    }

    @Override
    public void delete(Short id) {
        log.debug("Request to delete product : {}", id);

        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id));

        if (Objects.nonNull(product.getPicture())) {
            this.fileStorageService.delete(product.getPicture());
        }

        this.productRepository.delete(product);
    }

    @Override
    public List<ProductImageDto> bulkFindAll(Short id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<ProductImage> productImages = this.productImageRepository.findAllByProduct(product);

        return productImages.stream()
                .map(ProductServiceImpl::mapToImageDto)
                .toList();
    }

    @Transactional
    @Override
    public List<ProductImageDto> bulkCreate(Short id, MultipartFile[] files, List<String> filenames) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id));

        this.productImageRepository.deleteByProduct(product);

        List<ProductImage> productImages = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            ProductImage productImage = new ProductImage();
            productImage.setFileName(filenames.get(i));
            productImage.setFileSize(files[i].getSize());
            productImage.setFileType(files[i].getContentType());
            productImage.setFileUri("http://localhost:8080");
            productImage.setProduct(product);

            productImages.add(productImage);
        }

        this.productImageRepository.saveAll(productImages);

        return productImages.stream()
                .map(ProductServiceImpl::mapToImageDto)
                .toList();
    }

    public static ProductDto mapToDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getSupplier().getSupplierId(),
                product.getCategory().getCategoryId(),
                product.getQuantityPerUnit(),
                product.getUnitPrice(),
                product.getUnitsInStock(),
                product.getUnitsInOrder(),
                product.getReorderLevel(),
                product.getDiscontinued(),
                product.getPicture(),
                product.getCreateDate(),
                product.getModifiedDate()
        );
    }

    public static ProductImageDto mapToImageDto(ProductImage productImage) {
        return new ProductImageDto(
                productImage.getImageId(),
                productImage.getFileName(),
                productImage.getFileSize(),
                productImage.getFileType(),
                productImage.getFileUri(),
                productImage.getProduct().getProductId()
        );
    }
}
