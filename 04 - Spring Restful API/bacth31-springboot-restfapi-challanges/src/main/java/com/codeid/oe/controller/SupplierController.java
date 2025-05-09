package com.codeid.oe.controller;

import com.codeid.oe.model.dto.SupplierDto;
import com.codeid.oe.service.BaseCrudService;
import com.codeid.oe.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/suppliers")
@Slf4j
@RequiredArgsConstructor
public class SupplierController extends BaseCrudController<SupplierDto, Short> {

    private final SupplierService suppliersService;

    @Override
    protected BaseCrudService<SupplierDto, Short> getService() {
        return suppliersService;
    }
}
