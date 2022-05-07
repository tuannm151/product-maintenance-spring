package com.example.productmtspring.controller;

import com.example.productmtspring.model.Product;
import com.example.productmtspring.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Optional;

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
    @GetMapping("/products/edit")
    public String edit(@RequestParam("productCode") String action, Model model) {
        model.addAttribute("action", "edit");
        Optional<Product> product = productRepository.findById(action);
        if(product.isEmpty()) {
            return "products";
        }
        model.addAttribute("product", product.get());
        return "edit";
    }

    @GetMapping("/products/add")
    public String add(Model model, Product product) {
        model.addAttribute("action", "add");
        return "add";
    }

    @GetMapping("/products/delete")
    public String delete(@RequestParam("productCode") String productCode, Model model) {
        model.addAttribute("action", "delete");
        Optional<Product> productOptional = productRepository.findById(productCode);
        if(productOptional.isEmpty()) {
            return "products";
        }
        model.addAttribute("product", productOptional.get());
        return "delete";
    }

    @PostMapping("/products/edit")
    public String edit(@RequestParam("productCode") String productCode, @ModelAttribute Product product) {
        Optional<Product> productOptional = productRepository.findById(productCode);
        if(productOptional.isEmpty()) {
            return "redirect:/products";
        }
        Product productToUpdate = productOptional.get();
        productToUpdate.setCode(product.getCode());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productRepository.save(productToUpdate);
        return "redirect:/products";
    }
    @PostMapping("/products/add")
    public String add(@ModelAttribute Product product, Model model) {
        if(productRepository.findById(product.getCode()).isPresent()) {

            Product productToUpdate = new Product();
            productToUpdate.setCode(null);
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setDescription(product.getDescription());
            model.addAttribute("error", "Product already exists");
            model.addAttribute("product", productToUpdate);
            return "add";
        }
        productRepository.save(product);
        return "redirect:/products";
    }
    @PostMapping("/products/delete")
    public String delete(@RequestParam(value = "productCode", required = false) String productCode, @RequestParam(value = "action", required = false) String action) {
        if(action != null && action.equals("cancel")) {
            return "redirect:/products";
        }
        Optional<Product> productOptional = productRepository.findById(productCode);
        if(productOptional.isEmpty()) {
            return "redirect:/products";
        }
        productRepository.delete(productOptional.get());
        return "redirect:/products";
    }
}
