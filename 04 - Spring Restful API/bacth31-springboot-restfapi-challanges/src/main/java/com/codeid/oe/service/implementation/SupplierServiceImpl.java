package com.codeid.oe.service.implementation;

import com.codeid.oe.model.dto.SupplierDto;
import com.codeid.oe.model.entity.Supplier;
import com.codeid.oe.repository.SupplierRepository;
import com.codeid.oe.service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public List<SupplierDto> findAll() {
        log.debug("Request fetching data supplier");

        return this.supplierRepository.findAll()
                .stream()
                .map(SupplierServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDto findById(Short id) {
        log.debug("Request to get supplier : {}", id);

        Supplier supplier = this.supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("supplier not found with id " + id));

        return mapToDto(supplier);
    }

    @Override
    public SupplierDto save(SupplierDto entity) {
        log.debug("Request to create supplier : {}", entity);

        Supplier supplier = new Supplier();
        supplier.setCompanyName(entity.getCompanyName());
        supplier.setContactName(entity.getContactName());
        supplier.setContactTitle(entity.getContactTitle());
        supplier.setPhone(entity.getPhone());
        supplier.setFax(entity.getFax());
        supplier.setHomepage(entity.getHomepage());
        supplierRepository.save(supplier);

        return mapToDto(supplier);
    }

    @Override
    public SupplierDto update(Short id, SupplierDto entity) {
        log.debug("Request to update supplier : {}", id);

        Supplier supplier = this.supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("supplier not found with id " + id));
        supplier.setCompanyName(entity.getCompanyName());
        supplier.setContactName(entity.getContactName());
        supplier.setContactTitle(entity.getContactTitle());
        supplier.setPhone(entity.getPhone());
        supplier.setFax(entity.getFax());
        supplier.setHomepage(entity.getHomepage());
        supplierRepository.save(supplier);

        return mapToDto(supplier);
    }

    @Override
    public void delete(Short id) {
        log.debug("Request to delete supplier : {}", id);

        Supplier supplier = this.supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("supplier not found with id " + id));

        this.supplierRepository.delete(supplier);
    }

    public static SupplierDto mapToDto(Supplier suppliers) {
        return new SupplierDto(
                suppliers.getSupplierId(),
                suppliers.getCompanyName(),
                suppliers.getContactName(),
                suppliers.getContactTitle(),
                suppliers.getPhone(),
                suppliers.getFax(),
                suppliers.getHomepage()
        );
    }
}
