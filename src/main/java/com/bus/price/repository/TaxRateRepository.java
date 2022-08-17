package com.bus.price.repository;

import com.bus.price.domain.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {
    TaxRate findByTaxDate(LocalDate taxDate);
}
