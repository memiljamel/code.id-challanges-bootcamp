package com.codeid.eshopay.controller;

import com.codeid.eshopay.model.request.CreateSupplierRequest;
import com.codeid.eshopay.model.request.UpdateSupplierRequest;
import com.codeid.eshopay.model.response.SupplierResponse;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/suppliers")
public class SupplierController extends BaseCrudController<
        CreateSupplierRequest,
        UpdateSupplierRequest,
        SupplierResponse,
        Short> {

    @Autowired
    private SupplierService supplierService;

    @Override
    protected BaseCrudService<CreateSupplierRequest, UpdateSupplierRequest, SupplierResponse, Short> getService() {
        return supplierService;
    }
}
