package com.webrayan.agent.repository;

import com.webrayan.agent.entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private final List<Product> products = List.of(

            new Product(
                    1L,
                    "MacBook Pro M4",
                    "14-inch laptop with Apple M4 chip",
                    "Laptop",
                    120_000_000,
                    5
            ),

            new Product(
                    2L,
                    "Dell XPS 15",
                    "15-inch laptop with Intel Core Ultra 7",
                    "Laptop",
                    95_000_000,
                    3
            )
    );

    public Optional<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.name().equalsIgnoreCase(name))
                .findFirst();
    }

}
