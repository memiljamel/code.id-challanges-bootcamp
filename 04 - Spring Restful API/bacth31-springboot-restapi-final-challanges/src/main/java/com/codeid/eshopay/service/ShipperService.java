package com.codeid.eshopay.service;

import com.codeid.eshopay.model.request.CreateShipperRequest;
import com.codeid.eshopay.model.request.UpdateShipperRequest;
import com.codeid.eshopay.model.response.ShipperResponse;

public interface ShipperService extends BaseCrudService<
        CreateShipperRequest,
        UpdateShipperRequest,
        ShipperResponse,
        Short> {
}
