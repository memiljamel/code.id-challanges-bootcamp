package com.codeid.eshopay.service.implementation;

import com.codeid.eshopay.model.entity.oe.Supplier;
import com.codeid.eshopay.model.request.CreateSupplierRequest;
import com.codeid.eshopay.model.request.UpdateSupplierRequest;
import com.codeid.eshopay.model.response.SupplierResponse;
import com.codeid.eshopay.repository.SupplierRepository;
import com.codeid.eshopay.service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<SupplierResponse> findAll(Pageable pageable) {
        Page<Supplier> suppliers = supplierRepository.findAll(pageable);

        return suppliers.getContent().stream()
                .map(SupplierServiceImpl::mapToSupplierResponse)
                .toList();
    }

    @Override
    public SupplierResponse findById(Short id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + id));

        return mapToSupplierResponse(supplier);
    }

    @Override
    public SupplierResponse save(CreateSupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(request.getCompanyName());
        supplier.setContactName(request.getContactName());
        supplier.setContactTitle(request.getContactTitle());
        supplier.setPhone(request.getPhone());
        supplier.setFax(request.getFax());
        supplier.setHomepage(request.getHomepage());
        supplierRepository.save(supplier);

        return mapToSupplierResponse(supplier);
    }

    @Override
    public SupplierResponse update(UpdateSupplierRequest request) {
        Supplier supplier = supplierRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + request.getId()));
        supplier.setCompanyName(request.getCompanyName());
        supplier.setContactName(request.getContactName());
        supplier.setContactTitle(request.getContactTitle());
        supplier.setPhone(request.getPhone());
        supplier.setFax(request.getFax());
        supplier.setHomepage(request.getHomepage());
        supplierRepository.save(supplier);

        return mapToSupplierResponse(supplier);
    }

    @Override
    public void delete(Short id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + id));

        supplierRepository.delete(supplier);
    }

    protected static SupplierResponse mapToSupplierResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .supplierId(supplier.getSupplierId())
                .companyName(supplier.getCompanyName())
                .contactName(supplier.getContactName())
                .contactTitle(supplier.getContactTitle())
                .phone(supplier.getPhone())
                .fax(supplier.getFax())
                .homepage(supplier.getHomepage())
                .createdDate(supplier.getCreatedDate())
                .modifiedDate(supplier.getModifiedDate())
                .build();
    }
}
