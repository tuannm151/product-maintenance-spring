package com.example.productmtspring.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class Product {

    private String code;
    private String description;
    private double price;



}
