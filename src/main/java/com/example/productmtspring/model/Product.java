package com.example.productmtspring.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class Product {

    private String code;
    private String description;
    private BigDecimal price;



}
