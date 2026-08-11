package com.webrayan.agent.tools;

import com.webrayan.agent.entity.Product;
import com.webrayan.agent.repository.ProductRepository;

public class ProductTool {
    private final ProductRepository repository;

    public ProductTool(ProductRepository repository) {
        this.repository = repository;
    }

    public Product getProduct(String productName) {
        return repository.findByName(productName).orElse(null);
    }

}
