package com.codeid.eshopay.service.implementation;

import com.codeid.eshopay.model.entity.oe.Category;
import com.codeid.eshopay.model.entity.oe.Product;
import com.codeid.eshopay.model.entity.oe.ProductImage;
import com.codeid.eshopay.model.entity.oe.Supplier;
import com.codeid.eshopay.model.request.BulkProductImageRequest;
import com.codeid.eshopay.model.request.CreateProductRequest;
import com.codeid.eshopay.model.request.UpdateProductRequest;
import com.codeid.eshopay.model.response.CategoryResponse;
import com.codeid.eshopay.model.response.ProductImageResponse;
import com.codeid.eshopay.model.response.ProductResponse;
import com.codeid.eshopay.model.response.SupplierResponse;
import com.codeid.eshopay.repository.CategoryRepository;
import com.codeid.eshopay.repository.ProductImageRepository;
import com.codeid.eshopay.repository.ProductRepository;
import com.codeid.eshopay.repository.SupplierRepository;
import com.codeid.eshopay.service.FileStorageService;
import com.codeid.eshopay.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;

import static com.codeid.eshopay.service.implementation.CategoryServiceImpl.mapToCategoryResponse;
import static com.codeid.eshopay.service.implementation.SupplierServiceImpl.mapToSupplierResponse;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public List<ProductResponse> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        return products.getContent().stream()
                .map(ProductServiceImpl::mapToProductResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findAllWithCategory(String keyword, Pageable pageable) {
        List<Product> products = productRepository.findByProductNameOrCategoryName(keyword, pageable);

        return products.stream()
                .map(ProductServiceImpl::mapToProductResponse)
                .toList();
    }

    @Override
    public ProductResponse findById(Short id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));

        return mapToProductResponse(product);
    }

    @Override
    public ProductResponse save(CreateProductRequest request) {
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + request.getSupplierId()));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + request.getCategoryId()));

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(request.getQuantityPerUnit());
        product.setUnitPrice(request.getUnitPrice());
        product.setUnitsInStock(request.getUnitsInStock());
        product.setUnitsInOrder(request.getUnitsInOrder());
        product.setReorderLevel(request.getReorderLevel());
        product.setDiscontinued(request.getDiscontinued());

        if (Objects.nonNull(request.getPicture())) {
            String filename = fileStorageService.save(request.getPicture());
            product.setPicture(filename);
        }

        productRepository.save(product);

        return mapToProductResponse(product);
    }

    @Override
    public ProductResponse update(UpdateProductRequest request) {
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + request.getSupplierId()));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + request.getCategoryId()));

        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + request.getId()));
        product.setProductName(request.getProductName());
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(request.getQuantityPerUnit());
        product.setUnitPrice(request.getUnitPrice());
        product.setUnitsInStock(request.getUnitsInStock());
        product.setUnitsInOrder(request.getUnitsInOrder());
        product.setReorderLevel(request.getReorderLevel());
        product.setDiscontinued(request.getDiscontinued());

        if (Objects.nonNull(request.getPicture())) {
            fileStorageService.delete(product.getPicture());

            String filename = fileStorageService.save(request.getPicture());
            product.setPicture(filename);
        }

        productRepository.save(product);

        return mapToProductResponse(product);
    }

    @Override
    public void delete(Short id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));

        if (Objects.nonNull(product.getPicture())) {
            fileStorageService.delete(product.getPicture());
        }

        productRepository.delete(product);
    }

    @Override
    public void delete(Short productId, Short imageId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));

        ProductImage productImage = productImageRepository.findByProductAndImageId(product, imageId)
                .orElseThrow(() -> new EntityNotFoundException("Product Image not found with id " + imageId));

        fileStorageService.delete(productImage.getFileName());

        productImageRepository.delete(productImage);
    }

    @Transactional
    @Override
    public List<ProductImageResponse> bulkInsert(BulkProductImageRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + request.getProductId()));

        if (!request.getMerge()) {
            product.getProductImages().forEach(productImage ->
                    fileStorageService.delete(productImage.getFileName()));

            productImageRepository.deleteByProduct(product);
        }

        List<ProductImage> productImages = request.getImages().stream()
                .map(image -> {
                    String filename = fileStorageService.save(image);

                    String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/products/files/")
                            .path(filename)
                            .toUriString();

                    ProductImage productImage = new ProductImage();
                    productImage.setFileName(filename);
                    productImage.setFileSize(image.getSize());
                    productImage.setFileType(image.getContentType());
                    productImage.setFileUri(fileUri);
                    productImage.setProduct(product);

                    return productImage;
                })
                .toList();

        productImageRepository.saveAll(productImages);

        return productImages.stream()
                .map(ProductServiceImpl::mapToProductImageResponse)
                .toList();
    }

    protected static ProductResponse mapToProductResponse(Product product) {
        SupplierResponse supplier = mapToSupplierResponse(product.getSupplier());

        CategoryResponse category = mapToCategoryResponse(product.getCategory());

        List<String> productImageUrls = product.getProductImages().stream()
                .map(ProductImage::getFileName)
                .toList();

        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .supplier(supplier)
                .category(category)
                .quantityPerUnit(product.getQuantityPerUnit())
                .unitPrice(product.getUnitPrice())
                .unitsInStock(product.getUnitsInStock())
                .unitsInOrder(product.getUnitsInOrder())
                .reorderLevel(product.getReorderLevel())
                .discontinued(product.getDiscontinued())
                .pictureUrl(product.getPicture())
                .productImageUrls(productImageUrls)
                .createdDate(product.getCreatedDate())
                .modifiedDate(product.getModifiedDate())
                .build();
    }

    protected static ProductImageResponse mapToProductImageResponse(ProductImage productImage) {
        return ProductImageResponse.builder()
                .imageId(productImage.getImageId())
                .fileName(productImage.getFileName())
                .fileSize(productImage.getFileSize())
                .fileType(productImage.getFileType())
                .fileUri(productImage.getFileUri())
                .createdDate(productImage.getCreatedDate())
                .modifiedDate(productImage.getModifiedDate())
                .build();
    }
}
