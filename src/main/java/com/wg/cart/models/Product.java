package com.wg.cart.models;

import com.wg.cart.config.Category;
import com.wg.cart.config.Products;


public class Product {
    private String name;
    private Double price;
    private Category category;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
        this.category = Products.products.get(name);
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}
