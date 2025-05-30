package com.codeid.eshopay.service.implementation;

import com.codeid.eshopay.model.entity.oe.Shipper;
import com.codeid.eshopay.model.request.CreateShipperRequest;
import com.codeid.eshopay.model.request.UpdateShipperRequest;
import com.codeid.eshopay.model.response.ShipperResponse;
import com.codeid.eshopay.repository.ShipperRepository;
import com.codeid.eshopay.service.ShipperService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {

    @Autowired
    private ShipperRepository shipperRepository;

    @Override
    public List<ShipperResponse> findAll(Pageable pageable) {
        Page<Shipper> shippers = shipperRepository.findAll(pageable);

        return shippers.getContent().stream()
                .map(ShipperServiceImpl::mapToShipperResponse)
                .toList();
    }

    @Override
    public ShipperResponse findById(Short id) {
        Shipper shipper = shipperRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shipper not found with id " + id));

        return mapToShipperResponse(shipper);
    }

    @Override
    public ShipperResponse save(CreateShipperRequest request) {
        Shipper shipper = new Shipper();
        shipper.setCompanyName(request.getCompanyName());
        shipper.setPhone(request.getPhone());
        shipperRepository.save(shipper);

        return mapToShipperResponse(shipper);
    }

    @Override
    public ShipperResponse update(UpdateShipperRequest request) {
        Shipper shipper = shipperRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Shipper not found with id " + request.getId()));
        shipper.setCompanyName(request.getCompanyName());
        shipper.setPhone(request.getPhone());
        shipperRepository.save(shipper);

        return mapToShipperResponse(shipper);
    }

    @Override
    public void delete(Short id) {
        Shipper shipper = shipperRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shipper not found with id " + id));

        shipperRepository.delete(shipper);
    }

    protected static ShipperResponse mapToShipperResponse(Shipper shipper) {
        return ShipperResponse.builder()
                .shipperId(shipper.getShipperId())
                .companyName(shipper.getCompanyName())
                .phone(shipper.getPhone())
                .createdDate(shipper.getCreatedDate())
                .modifiedDate(shipper.getModifiedDate())
                .build();
    }
}
