package com.example.productmtspring.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    private String description;
    private BigDecimal price;
}
