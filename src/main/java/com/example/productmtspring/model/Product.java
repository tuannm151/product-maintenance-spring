package com.example.productmtspring.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    private String code;
    private String description;
    private BigDecimal price;
}
