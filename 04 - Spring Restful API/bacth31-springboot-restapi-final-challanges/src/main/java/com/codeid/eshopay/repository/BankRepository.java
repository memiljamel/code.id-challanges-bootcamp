package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.fintech.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {
}
