package com.example.productmtspring.controller;

import com.example.productmtspring.model.Product;
import com.example.productmtspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ProductController {
    private final ProductRepository productRepository;
    public ProductController (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        ArrayList<Product> products = (ArrayList<Product>) productRepository.findAll();
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
}
