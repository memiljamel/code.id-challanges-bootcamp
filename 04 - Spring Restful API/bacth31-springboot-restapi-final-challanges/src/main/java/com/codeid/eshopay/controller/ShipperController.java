package com.codeid.eshopay.controller;

import com.codeid.eshopay.model.request.CreateShipperRequest;
import com.codeid.eshopay.model.request.UpdateShipperRequest;
import com.codeid.eshopay.model.response.ShipperResponse;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/shippers")
public class ShipperController extends BaseCrudController<
        CreateShipperRequest,
        UpdateShipperRequest,
        ShipperResponse,
        Short> {

    @Autowired
    private ShipperService shipperService;

    @Override
    protected BaseCrudService<CreateShipperRequest, UpdateShipperRequest, ShipperResponse, Short> getService() {
        return shipperService;
    }
}
