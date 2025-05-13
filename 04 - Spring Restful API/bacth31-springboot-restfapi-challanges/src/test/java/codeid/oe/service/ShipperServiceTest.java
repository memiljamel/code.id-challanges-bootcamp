package codeid.oe.service;

import com.codeid.oe.model.dto.ShipperDto;
import com.codeid.oe.model.entity.Shipper;
import com.codeid.oe.repository.ShipperRepository;
import com.codeid.oe.service.implementation.ShipperServiceImpl;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipperServiceTest {

    @Mock
    private ShipperRepository shipperRepository;

    @InjectMocks
    private ShipperServiceImpl shipperService;

    private static final Short SHIPPER_ID = 100;
    private static final String COMPANY_NAME = "Company";
    private static final String PHONE = "1234567890";

    @Test
    void findAll_whenShippersExists_shouldReturnPageOfShipperResponse() {
        // Arrange
        Shipper shipper = new Shipper();
        shipper.setShipperId(SHIPPER_ID);
        shipper.setCompanyName(COMPANY_NAME);
        shipper.setPhone(PHONE);

        List<Shipper> shippers = List.of(shipper);

        when(shipperRepository.findAll())
                .thenReturn(shippers);

        // Act
        List<ShipperDto> responses = shipperService.findAll();

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(SHIPPER_ID, responses.get(0).getShipperId());
        assertEquals(COMPANY_NAME, responses.get(0).getCompanyName());
        assertEquals(PHONE, responses.get(0).getPhone());

        verify(shipperRepository, times(1))
                .findAll();
    }

    @Test
    void findAll_whenShippersNotExists_shouldReturnEmptyOfShipperResponse() {
        // Arrange
        List<Shipper> shippers = List.of();

        when(shipperRepository.findAll())
                .thenReturn(shippers);

        // Act
        List<ShipperDto> responses = shipperService.findAll();

        // Assert
        assertNotNull(responses);
        assertEquals(0, responses.size());

        verify(shipperRepository, times(1))
                .findAll();
    }

    @Test
    void findOne_whenShipperExists_shouldReturnShipperResponse() {
        // Arrange
        Shipper shipper = new Shipper();
        shipper.setShipperId(SHIPPER_ID);
        shipper.setCompanyName(COMPANY_NAME);
        shipper.setPhone(PHONE);

        when(shipperRepository.findById(SHIPPER_ID))
                .thenReturn(Optional.of(shipper));

        // Act
        ShipperDto response = shipperService.findById(SHIPPER_ID);

        // Assert
        assertNotNull(response);
        assertEquals(SHIPPER_ID, response.getShipperId());
        assertEquals(COMPANY_NAME, response.getCompanyName());
        assertEquals(PHONE, response.getPhone());

        verify(shipperRepository, times(1))
                .findById(SHIPPER_ID);
    }

    @Test
    void findOne_whenShipperNotExists_shouldThrowResponseStatusException() {
        // Arrange
        when(shipperRepository.findById(SHIPPER_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            shipperService.findById(SHIPPER_ID);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(shipperRepository, times(1))
                .findById(SHIPPER_ID);
    }

    @Test
    void create_whenRequestIsValid_shouldSaveAndReturnShipperResponse() {
        // Arrange
        Shipper shipper = new Shipper();
        shipper.setShipperId(SHIPPER_ID);
        shipper.setCompanyName(COMPANY_NAME);
        shipper.setPhone(PHONE);

        ShipperDto request = new ShipperDto();
        request.setShipperId(SHIPPER_ID);
        request.setCompanyName(COMPANY_NAME);
        request.setPhone(PHONE);

        when(shipperRepository.save(any(Shipper.class)))
                .thenReturn(shipper);

        // Act
        ShipperDto response = shipperService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(SHIPPER_ID, response.getShipperId());
        assertEquals(COMPANY_NAME, response.getCompanyName());
        assertEquals(PHONE, response.getPhone());

        verify(shipperRepository, times(1))
                .save(any(Shipper.class));
    }

    @Test
    void update_whenShipperExists_shouldUpdateAndReturnShipperResponse() {
        // Arrange
        Shipper shipper = new Shipper();
        shipper.setShipperId(SHIPPER_ID);
        shipper.setCompanyName(COMPANY_NAME);
        shipper.setPhone(PHONE);

        ShipperDto request = new ShipperDto();
        request.setShipperId(SHIPPER_ID);
        request.setCompanyName("New Company");
        request.setPhone("0987654321");

        when(shipperRepository.findById(SHIPPER_ID))
                .thenReturn(Optional.of(shipper));
        when(shipperRepository.save(any(Shipper.class)))
                .thenReturn(shipper);

        // Act
        ShipperDto response = shipperService.update(SHIPPER_ID, request);

        // Assert
        assertNotNull(response);
        assertEquals(SHIPPER_ID, response.getShipperId());
        assertEquals("New Company", response.getCompanyName());
        assertEquals("0987654321", response.getPhone());

        verify(shipperRepository, times(1))
                .findById(SHIPPER_ID);
        verify(shipperRepository, times(1))
                .save(any(Shipper.class));
    }

    @Test
    void update_whenShipperNotExists_shouldThrowResponseStatusException() {
        // Arrange
        ShipperDto request = new ShipperDto();
        request.setShipperId(SHIPPER_ID);
        request.setCompanyName("New Company");
        request.setPhone("0987654321");

        when(shipperRepository.findById(SHIPPER_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            shipperService.update(SHIPPER_ID, request);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(shipperRepository, times(1))
                .findById(SHIPPER_ID);
        verify(shipperRepository, never())
                .save(any(Shipper.class));
    }

    @Test
    void remove_whenShipperExists_shouldDeleteShipper() {
        // Arrange
        Shipper shipper = new Shipper();
        shipper.setShipperId(SHIPPER_ID);
        shipper.setCompanyName(COMPANY_NAME);
        shipper.setPhone(PHONE);

        when(shipperRepository.findById(SHIPPER_ID))
                .thenReturn(Optional.of(shipper));
        doNothing().when(shipperRepository)
                .delete(shipper);

        // Act
        shipperService.delete(SHIPPER_ID);

        // Assert
        verify(shipperRepository, times(1))
                .findById(SHIPPER_ID);
        verify(shipperRepository, times(1))
                .delete(shipper);
    }

    @Test
    void remove_whenShipperNotExists_shouldThrowResponseStatusException() {
        // Arrange
        when(shipperRepository.findById(SHIPPER_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            shipperService.delete(SHIPPER_ID);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(shipperRepository, times(1))
                .findById(SHIPPER_ID);
        verify(shipperRepository, never())
                .delete(any(Shipper.class));
    }
}