package com.codeid.oe.controller;

import com.codeid.oe.model.dto.ShipperDto;
import com.codeid.oe.service.BaseCrudService;
import com.codeid.oe.service.ShipperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shippers")
@Slf4j
@RequiredArgsConstructor
public class ShipperController extends BaseCrudController<ShipperDto, Short> {

    private final ShipperService shippersService;

    @Override
    protected BaseCrudService<ShipperDto, Short> getService() {
        return shippersService;
    }
}
