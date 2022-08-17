package com.bus.price.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tax_rates")
public class TaxRate {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tax_date", nullable = false)
    private LocalDate taxDate;

    @Column(name = "tax_amount", nullable = false, precision = 4, scale = 2)
    private BigDecimal taxAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(LocalDate taxDate) {
        this.taxDate = taxDate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

}