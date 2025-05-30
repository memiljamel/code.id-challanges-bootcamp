package com.codeid.eshopay.service;

import com.codeid.eshopay.model.request.CreateSupplierRequest;
import com.codeid.eshopay.model.request.UpdateSupplierRequest;
import com.codeid.eshopay.model.response.SupplierResponse;

public interface SupplierService extends BaseCrudService<
        CreateSupplierRequest,
        UpdateSupplierRequest,
        SupplierResponse,
        Short> {
}
