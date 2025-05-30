package com.codeid.eshopay.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseCrudService<CreateReq, UpdateReq, Res, ID> {

    List<Res> findAll(Pageable pageable);

    Res findById(ID id);

    Res save(CreateReq request);

    Res update(UpdateReq request);

    void delete(ID id);
}
