package codeid.oe.service;

import com.codeid.oe.model.dto.ProductDto;
import com.codeid.oe.model.entity.Category;
import com.codeid.oe.model.entity.Product;
import com.codeid.oe.model.entity.Supplier;
import com.codeid.oe.repository.CategoryRepository;
import com.codeid.oe.repository.ProductRepository;
import com.codeid.oe.repository.SupplierRepository;
import com.codeid.oe.service.implementation.FileStorageService;
import com.codeid.oe.service.implementation.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private ProductServiceImpl productService;

    private final static Short PRODUCT_ID = 100;
    private final static String PRODUCT_NAME = "Product";
    private final static Short SUPPLIER_ID = 1;
    private final static Short CATEGORY_ID = 1;
    private final static String QUANTITY_PER_UNIT = "12 Unit / Box";
    private final static Float UNIT_PRICE = 1000000.0F;
    private final static Short UNITS_IN_STOCK = 500;
    private final static Short UNITS_IN_ORDER = 200;
    private final static Short REORDER_LEVEL = 25;
    private final static Integer DISCONTINUED = 0;
    private static final byte[] PICTURE = new byte[]{1, 2, 3};
    private static final String PICTURE_URL = "my-picture.png";

    @Test
    void findAll_whenProductsExists_shouldReturnPageOfProductResponse() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);

        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);

        Product product = new Product();
        product.setProductId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(QUANTITY_PER_UNIT);
        product.setUnitPrice(UNIT_PRICE);
        product.setUnitsInStock(UNITS_IN_STOCK);
        product.setUnitsInOrder(UNITS_IN_ORDER);
        product.setReorderLevel(REORDER_LEVEL);
        product.setDiscontinued(DISCONTINUED);
        product.setPicture(PICTURE_URL);

        List<Product> products = List.of(product);

        when(productRepository.findAll())
                .thenReturn(products);

        // Act
        List<ProductDto> responses = productService.findAll();

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(PRODUCT_ID, responses.get(0).getProductId());
        assertEquals(PRODUCT_NAME, responses.get(0).getProductName());
        assertEquals(SUPPLIER_ID, responses.get(0).getSupplierId());
        assertEquals(CATEGORY_ID, responses.get(0).getCategoryId());
        assertEquals(QUANTITY_PER_UNIT, responses.get(0).getQuantityPerUnit());
        assertEquals(UNIT_PRICE, responses.get(0).getUnitPrice());
        assertEquals(UNITS_IN_STOCK, responses.get(0).getUnitsInStock());
        assertEquals(UNITS_IN_ORDER, responses.get(0).getUnitsInOrder());
        assertEquals(REORDER_LEVEL, responses.get(0).getReorderLevel());
        assertEquals(DISCONTINUED, responses.get(0).getDiscontinued());
        assertEquals(PICTURE_URL, responses.get(0).getPicture());

        verify(productRepository, times(1))
                .findAll();
    }

    @Test
    void findAll_whenProductsNotExists_shouldReturnEmptyOfProductResponse() {
        // Arrange
        List<Product> products = List.of();

        when(productRepository.findAll())
                .thenReturn(products);

        // Act
        List<ProductDto> responses = productService.findAll();

        // Assert
        assertNotNull(responses);
        assertEquals(0, responses.size());

        verify(productRepository, times(1))
                .findAll();
    }

    @Test
    void findOne_whenProductExists_shouldReturnProductResponse() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);

        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);

        Product product = new Product();
        product.setProductId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(QUANTITY_PER_UNIT);
        product.setUnitPrice(UNIT_PRICE);
        product.setUnitsInStock(UNITS_IN_STOCK);
        product.setUnitsInOrder(UNITS_IN_ORDER);
        product.setReorderLevel(REORDER_LEVEL);
        product.setDiscontinued(DISCONTINUED);
        product.setPicture(PICTURE_URL);

        when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.of(product));

        // Act
        ProductDto response = productService.findById(PRODUCT_ID);

        // Assert
        assertNotNull(response);
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals(PRODUCT_NAME, response.getProductName());
        assertEquals(SUPPLIER_ID, response.getSupplierId());
        assertEquals(CATEGORY_ID, response.getCategoryId());
        assertEquals(QUANTITY_PER_UNIT, response.getQuantityPerUnit());
        assertEquals(UNIT_PRICE, response.getUnitPrice());
        assertEquals(UNITS_IN_STOCK, response.getUnitsInStock());
        assertEquals(UNITS_IN_ORDER, response.getUnitsInOrder());
        assertEquals(REORDER_LEVEL, response.getReorderLevel());
        assertEquals(DISCONTINUED, response.getDiscontinued());
        assertEquals(PICTURE_URL, response.getPicture());

        verify(productRepository, times(1))
                .findById(PRODUCT_ID);
    }

    @Test
    void findOne_whenProductNotExists_shouldThrowResponseStatusException() {
        // Arrange
        when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.findById(PRODUCT_ID);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(productRepository, times(1))
                .findById(PRODUCT_ID);
    }

    @Test
    void create_whenRequestIsValid_shouldSaveAndReturnProductResponse() throws Exception {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);

        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);

        Product product = new Product();
        product.setProductId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(QUANTITY_PER_UNIT);
        product.setUnitPrice(UNIT_PRICE);
        product.setUnitsInStock(UNITS_IN_STOCK);
        product.setUnitsInOrder(UNITS_IN_ORDER);
        product.setReorderLevel(REORDER_LEVEL);
        product.setDiscontinued(DISCONTINUED);
        product.setPicture(PICTURE_URL);

        ProductDto request = new ProductDto();
        request.setProductId(PRODUCT_ID);
        request.setProductName(PRODUCT_NAME);
        request.setSupplierId(SUPPLIER_ID);
        request.setCategoryId(CATEGORY_ID);
        request.setQuantityPerUnit(QUANTITY_PER_UNIT);
        request.setUnitPrice(UNIT_PRICE);
        request.setUnitsInStock(UNITS_IN_STOCK);
        request.setUnitsInOrder(UNITS_IN_ORDER);
        request.setReorderLevel(REORDER_LEVEL);
        request.setDiscontinued(DISCONTINUED);
        request.setPicture(PICTURE_URL);

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.of(supplier));
        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class)))
                .thenReturn(product);

        // Act
        ProductDto response = productService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals(PRODUCT_NAME, response.getProductName());
        assertEquals(SUPPLIER_ID, response.getSupplierId());
        assertEquals(CATEGORY_ID, response.getCategoryId());
        assertEquals(QUANTITY_PER_UNIT, response.getQuantityPerUnit());
        assertEquals(UNIT_PRICE, response.getUnitPrice());
        assertEquals(UNITS_IN_STOCK, response.getUnitsInStock());
        assertEquals(UNITS_IN_ORDER, response.getUnitsInOrder());
        assertEquals(REORDER_LEVEL, response.getReorderLevel());
        assertEquals(DISCONTINUED, response.getDiscontinued());
        assertEquals(PICTURE_URL, response.getPicture());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(productRepository, times(1))
                .save(any(Product.class));
    }

    @Test
    void create_whenSupplierNotExists_shouldThrowResponseStatusException() throws Exception {
        // Arrange
        ProductDto request = new ProductDto();
        request.setProductId(PRODUCT_ID);
        request.setProductName(PRODUCT_NAME);
        request.setSupplierId(SUPPLIER_ID);
        request.setCategoryId(CATEGORY_ID);
        request.setQuantityPerUnit(QUANTITY_PER_UNIT);
        request.setUnitPrice(UNIT_PRICE);
        request.setUnitsInStock(UNITS_IN_STOCK);
        request.setUnitsInOrder(UNITS_IN_ORDER);
        request.setReorderLevel(REORDER_LEVEL);
        request.setDiscontinued(DISCONTINUED);

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.save(request);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(categoryRepository, never())
                .findById(CATEGORY_ID);
        verify(productRepository, never())
                .save(any(Product.class));
        verify(fileStorageService, never())
                .storeFileWithRandomName(any(MultipartFile.class));
    }

    @Test
    void create_whenCategoryNotExists_shouldThrowResponseStatusException() throws Exception {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);

        ProductDto request = new ProductDto();
        request.setProductId(PRODUCT_ID);
        request.setProductName(PRODUCT_NAME);
        request.setSupplierId(SUPPLIER_ID);
        request.setCategoryId(CATEGORY_ID);
        request.setQuantityPerUnit(QUANTITY_PER_UNIT);
        request.setUnitPrice(UNIT_PRICE);
        request.setUnitsInStock(UNITS_IN_STOCK);
        request.setUnitsInOrder(UNITS_IN_ORDER);
        request.setReorderLevel(REORDER_LEVEL);
        request.setDiscontinued(DISCONTINUED);

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.of(supplier));
        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.save(request);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(productRepository, never())
                .save(any(Product.class));
        verify(fileStorageService, never())
                .storeFileWithRandomName(any(MultipartFile.class));
    }

    @Test
    void update_whenProductExists_shouldUpdateAndReturnProductResponse() throws Exception {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);

        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);

        Product product = new Product();
        product.setProductId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(QUANTITY_PER_UNIT);
        product.setUnitPrice(UNIT_PRICE);
        product.setUnitsInStock(UNITS_IN_STOCK);
        product.setUnitsInOrder(UNITS_IN_ORDER);
        product.setReorderLevel(REORDER_LEVEL);
        product.setDiscontinued(DISCONTINUED);
        product.setPicture(PICTURE_URL);

        ProductDto request = new ProductDto();
        request.setProductId(PRODUCT_ID);
        request.setProductName("New Product");
        request.setSupplierId(SUPPLIER_ID);
        request.setCategoryId(CATEGORY_ID);
        request.setQuantityPerUnit("24 Unit / Box");
        request.setUnitPrice(2000000.0F);
        request.setUnitsInStock((short) 1000);
        request.setUnitsInOrder((short) 400);
        request.setReorderLevel((short) 50);
        request.setDiscontinued(1);
        request.setPicture("new-my-picture.png");

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.of(supplier));
        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(category));
        when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class)))
                .thenReturn(product);
        doNothing().when(fileStorageService)
                .delete(PICTURE_URL);

        // Act
        ProductDto response = productService.update(PRODUCT_ID, request);

        // Assert
        assertNotNull(response);
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals("New Product", response.getProductName());
        assertEquals(SUPPLIER_ID, response.getSupplierId());
        assertEquals(CATEGORY_ID, response.getCategoryId());
        assertEquals("24 Unit / Box", response.getQuantityPerUnit());
        assertEquals(2000000.0F, response.getUnitPrice());
        assertEquals((short) 1000, response.getUnitsInStock());
        assertEquals((short) 400, response.getUnitsInOrder());
        assertEquals((short) 50, response.getReorderLevel());
        assertEquals(1, response.getDiscontinued());
        assertEquals("new-my-picture.png", response.getPicture());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(productRepository, times(1))
                .findById(PRODUCT_ID);
        verify(productRepository, times(1))
                .save(any(Product.class));
        verify(fileStorageService, times(1))
                .delete(PICTURE_URL);
    }

    @Test
    void update_whenSupplierNotExists_shouldThrowResponseStatusException() throws Exception {
        // Arrange
        ProductDto request = new ProductDto();
        request.setProductId(PRODUCT_ID);
        request.setProductName("New Product");
        request.setSupplierId(SUPPLIER_ID);
        request.setCategoryId(CATEGORY_ID);
        request.setQuantityPerUnit("24 Unit / Box");
        request.setUnitPrice(2000000.0F);
        request.setUnitsInStock((short) 1000);
        request.setUnitsInOrder((short) 400);
        request.setReorderLevel((short) 50);
        request.setDiscontinued(1);
        request.setPicture("new-my-picture.png");

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.update(PRODUCT_ID, request);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(categoryRepository, never())
                .findById(CATEGORY_ID);
        verify(productRepository, never())
                .findById(PRODUCT_ID);
        verify(productRepository, never())
                .save(any(Product.class));
        verify(fileStorageService, never())
                .storeFileWithRandomName(any(MultipartFile.class));
    }

    @Test
    void update_whenCategoryNotExists_shouldThrowResponseStatusException() throws Exception {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);

        ProductDto request = new ProductDto();
        request.setProductId(PRODUCT_ID);
        request.setProductName("New Product");
        request.setSupplierId(SUPPLIER_ID);
        request.setCategoryId(CATEGORY_ID);
        request.setQuantityPerUnit("24 Unit / Box");
        request.setUnitPrice(2000000.0F);
        request.setUnitsInStock((short) 1000);
        request.setUnitsInOrder((short) 400);
        request.setReorderLevel((short) 50);
        request.setDiscontinued(1);
        request.setPicture("new-my-picture.png");

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.of(supplier));
        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.update(PRODUCT_ID, request);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(productRepository, never())
                .findById(PRODUCT_ID);
        verify(productRepository, never())
                .save(any(Product.class));
        verify(fileStorageService, never())
                .storeFileWithRandomName(any(MultipartFile.class));
    }

    @Test
    void update_whenProductNotExists_shouldThrowResponseStatusException() throws Exception {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);

        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);

        ProductDto request = new ProductDto();
        request.setProductId(PRODUCT_ID);
        request.setProductName("New Product");
        request.setSupplierId(SUPPLIER_ID);
        request.setCategoryId(CATEGORY_ID);
        request.setQuantityPerUnit("24 Unit / Box");
        request.setUnitPrice(2000000.0F);
        request.setUnitsInStock((short) 1000);
        request.setUnitsInOrder((short) 400);
        request.setReorderLevel((short) 50);
        request.setDiscontinued(1);
        request.setPicture("new-my-picture.png");

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.of(supplier));
        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(category));
        when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.update(PRODUCT_ID, request);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(productRepository, times(1))
                .findById(PRODUCT_ID);
        verify(productRepository, never())
                .save(any(Product.class));
        verify(fileStorageService, never())
                .storeFileWithRandomName(any(MultipartFile.class));

    }

    @Test
    void remove_whenProductExists_shouldDeleteProduct() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);

        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);

        Product product = new Product();
        product.setProductId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityPerUnit(QUANTITY_PER_UNIT);
        product.setUnitPrice(UNIT_PRICE);
        product.setUnitsInStock(UNITS_IN_STOCK);
        product.setUnitsInOrder(UNITS_IN_ORDER);
        product.setReorderLevel(REORDER_LEVEL);
        product.setDiscontinued(DISCONTINUED);
        product.setPicture(PICTURE_URL);

        when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.of(product));
        doNothing().when(fileStorageService)
                .delete(PICTURE_URL);
        doNothing().when(productRepository)
                .delete(product);

        // Act
        productService.delete(PRODUCT_ID);

        // Assert
        verify(productRepository, times(1))
                .findById(PRODUCT_ID);
        verify(fileStorageService, times(1))
                .delete(PICTURE_URL);
        verify(productRepository, times(1))
                .delete(product);
    }

    @Test
    void remove_whenProductNotExists_shouldThrowResponseStatusException() {
        // Arrange
        when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.delete(PRODUCT_ID);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(productRepository, times(1))
                .findById(PRODUCT_ID);
        verify(fileStorageService, never())
                .delete(any(String.class));
        verify(productRepository, never())
                .delete(any(Product.class));
    }
}