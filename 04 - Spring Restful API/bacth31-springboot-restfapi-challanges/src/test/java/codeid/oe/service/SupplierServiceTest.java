package codeid.oe.service;

import com.codeid.oe.model.dto.SupplierDto;
import com.codeid.oe.model.entity.Supplier;
import com.codeid.oe.repository.SupplierRepository;
import com.codeid.oe.service.implementation.SupplierServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    private final static Short SUPPLIER_ID = 100;
    private final static String COMPANY_NAME = "Company";
    private final static String CONTACT_NAME = "Contact";
    private final static String CONTACT_TITLE = "Title";
    private final static String PHONE = "1234567890";
    private final static String FAX = "1234567890";
    private final static String HOMEPAGE = "https://example.com";

    @Test
    void findAll_whenSuppliersExists_shouldReturnPageOfSupplierResponse() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);
        supplier.setCompanyName(COMPANY_NAME);
        supplier.setContactName(CONTACT_NAME);
        supplier.setContactTitle(CONTACT_TITLE);
        supplier.setPhone(PHONE);
        supplier.setFax(FAX);
        supplier.setHomepage(HOMEPAGE);

        List<Supplier> suppliers = List.of(supplier);

        when(supplierRepository.findAll())
                .thenReturn(suppliers);

        // Act
        List<SupplierDto> responses = supplierService.findAll();

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(SUPPLIER_ID, responses.get(0).getSupplierId());
        assertEquals(COMPANY_NAME, responses.get(0).getCompanyName());
        assertEquals(CONTACT_NAME, responses.get(0).getContactName());
        assertEquals(CONTACT_TITLE, responses.get(0).getContactTitle());
        assertEquals(PHONE, responses.get(0).getPhone());
        assertEquals(FAX, responses.get(0).getFax());
        assertEquals(HOMEPAGE, responses.get(0).getHomepage());

        verify(supplierRepository, times(1))
                .findAll();
    }

    @Test
    void findAll_whenShippersNotExists_shouldReturnEmptyOfShipperResponse() {
        // Arrange
        List<Supplier> suppliers = List.of();

        when(supplierRepository.findAll())
                .thenReturn(suppliers);

        // Act
        List<SupplierDto> responses = supplierService.findAll();

        // Assert
        assertNotNull(responses);
        assertEquals(0, responses.size());

        verify(supplierRepository, times(1))
                .findAll();
    }

    @Test
    void findOne_whenSupplierExists_shouldReturnSupplierResponse() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);
        supplier.setCompanyName(COMPANY_NAME);
        supplier.setContactName(CONTACT_NAME);
        supplier.setContactTitle(CONTACT_TITLE);
        supplier.setPhone(PHONE);
        supplier.setFax(FAX);
        supplier.setHomepage(HOMEPAGE);

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.of(supplier));

        // Act
        SupplierDto response = supplierService.findById(SUPPLIER_ID);

        // Assert
        assertNotNull(response);
        assertEquals(SUPPLIER_ID, response.getSupplierId());
        assertEquals(COMPANY_NAME, response.getCompanyName());
        assertEquals(CONTACT_NAME, response.getContactName());
        assertEquals(CONTACT_TITLE, response.getContactTitle());
        assertEquals(PHONE, response.getPhone());
        assertEquals(FAX, response.getFax());
        assertEquals(HOMEPAGE, response.getHomepage());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
    }

    @Test
    void findOne_whenSupplierNotExists_shouldThrowResponseStatusException() {
        // Arrange
        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            supplierService.findById(SUPPLIER_ID);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
    }

    @Test
    void create_whenRequestIsValid_shouldSaveAndReturnSupplierResponse() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);
        supplier.setCompanyName(COMPANY_NAME);
        supplier.setContactName(CONTACT_NAME);
        supplier.setContactTitle(CONTACT_TITLE);
        supplier.setPhone(PHONE);
        supplier.setFax(FAX);
        supplier.setHomepage(HOMEPAGE);

        SupplierDto request = new SupplierDto();
        request.setSupplierId(SUPPLIER_ID);
        request.setCompanyName(COMPANY_NAME);
        request.setContactName(CONTACT_NAME);
        request.setContactTitle(CONTACT_TITLE);
        request.setPhone(PHONE);
        request.setFax(FAX);
        request.setHomepage(HOMEPAGE);

        when(supplierRepository.save(any(Supplier.class)))
                .thenReturn(supplier);

        // Act
        SupplierDto response = supplierService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(SUPPLIER_ID, response.getSupplierId());
        assertEquals(COMPANY_NAME, response.getCompanyName());
        assertEquals(CONTACT_NAME, response.getContactName());
        assertEquals(CONTACT_TITLE, response.getContactTitle());
        assertEquals(PHONE, response.getPhone());
        assertEquals(FAX, response.getFax());
        assertEquals(HOMEPAGE, response.getHomepage());

        verify(supplierRepository, times(1))
                .save(any(Supplier.class));
    }

    @Test
    void update_whenSupplierExists_shouldUpdateAndReturnSupplierResponse() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);
        supplier.setCompanyName(COMPANY_NAME);
        supplier.setContactName(CONTACT_NAME);
        supplier.setContactTitle(CONTACT_TITLE);
        supplier.setPhone(PHONE);
        supplier.setFax(FAX);
        supplier.setHomepage(HOMEPAGE);

        SupplierDto request = new SupplierDto();
        request.setSupplierId(SUPPLIER_ID);
        request.setCompanyName("New Company");
        request.setContactName("New Contact");
        request.setContactTitle("New Title");
        request.setPhone("0987654321");
        request.setFax("0987654321");
        request.setHomepage("https://new-example.com");

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.of(supplier));
        when(supplierRepository.save(any(Supplier.class)))
                .thenReturn(supplier);

        // Act
        SupplierDto response = supplierService.update(SUPPLIER_ID, request);

        // Assert
        assertNotNull(response);
        assertEquals(SUPPLIER_ID, response.getSupplierId());
        assertEquals("New Company", response.getCompanyName());
        assertEquals("New Contact", response.getContactName());
        assertEquals("New Title", response.getContactTitle());
        assertEquals("0987654321", response.getPhone());
        assertEquals("0987654321", response.getFax());
        assertEquals("https://new-example.com", response.getHomepage());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(supplierRepository, times(1))
                .save(any(Supplier.class));
    }

    @Test
    void update_whenSupplierNotExists_shouldThrowResponseStatusException() {
        // Arrange
        SupplierDto request = new SupplierDto();
        request.setSupplierId(SUPPLIER_ID);
        request.setCompanyName("New Company");
        request.setContactName("New Contact");
        request.setContactTitle("New Title");
        request.setPhone("0987654321");
        request.setFax("0987654321");
        request.setHomepage("https://new-example.com");

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            supplierService.update(SUPPLIER_ID, request);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(supplierRepository, never())
                .save(any(Supplier.class));
    }

    @Test
    void remove_whenSupplierExists_shouldDeleteSupplier() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setSupplierId(SUPPLIER_ID);
        supplier.setCompanyName(COMPANY_NAME);
        supplier.setContactName(CONTACT_NAME);
        supplier.setContactTitle(CONTACT_TITLE);
        supplier.setPhone(PHONE);
        supplier.setFax(FAX);
        supplier.setHomepage(HOMEPAGE);

        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.of(supplier));
        doNothing().when(supplierRepository)
                .delete(supplier);

        // Act
        supplierService.delete(SUPPLIER_ID);

        // Assert
        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(supplierRepository, times(1))
                .delete(supplier);
    }

    @Test
    void remove_whenSupplierNotExists_shouldThrowResponseStatusException() {
        // Arrange
        when(supplierRepository.findById(SUPPLIER_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            supplierService.delete(SUPPLIER_ID);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(supplierRepository, times(1))
                .findById(SUPPLIER_ID);
        verify(supplierRepository, never())
                .delete(any(Supplier.class));
    }
}