package com.codeid.oe.service.implementation;

import com.codeid.oe.model.dto.ShipperDto;
import com.codeid.oe.model.entity.Shipper;
import com.codeid.oe.repository.ShipperRepository;
import com.codeid.oe.service.ShipperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipperServiceImpl implements ShipperService {

    private final ShipperRepository shipperRepository;

    @Override
    public List<ShipperDto> findAll() {
        log.debug("request fetching data shipper");

        return this.shipperRepository.findAll()
                .stream()
                .map(ShipperServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShipperDto findById(Short id) {
        log.debug("Request to get shipper : {}", id);

        Shipper shipper = this.shipperRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shipper not found with id " + id));

        return mapToDto(shipper);
    }

    @Override
    public ShipperDto save(ShipperDto entity) {
        log.debug("Request to create shipper : {}", entity);

        Shipper shipper = new Shipper();
        shipper.setCompanyName(entity.getCompanyName());
        shipper.setPhone(entity.getPhone());

        return mapToDto(this.shipperRepository.save(shipper));
    }

    @Override
    public ShipperDto update(Short id, ShipperDto entity) {
        log.debug("Request to update shipper : {}", id);

        Shipper shipper = this.shipperRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shipper not found with id " + id));
        shipper.setCompanyName(entity.getCompanyName());
        shipper.setPhone(entity.getPhone());

        return mapToDto(this.shipperRepository.save(shipper));
    }

    @Override
    public void delete(Short id) {
        log.debug("Request to delete shipper : {}", id);

        Shipper shipper = this.shipperRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shipper not found with id " + id));

        this.shipperRepository.delete(shipper);
    }

    public static ShipperDto mapToDto(Shipper shipper) {
        return new ShipperDto(
                shipper.getShipperId(),
                shipper.getCompanyName(),
                shipper.getPhone(),
                shipper.getCreateDate(),
                shipper.getModifiedDate()
        );
    }
}
