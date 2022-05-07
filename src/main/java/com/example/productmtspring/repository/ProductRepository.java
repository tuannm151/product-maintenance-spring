package com.example.productmtspring.repository;

import com.example.productmtspring.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

}
