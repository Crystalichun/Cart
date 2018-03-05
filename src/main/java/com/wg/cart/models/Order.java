package com.wg.cart.models;

public class Order {
    private Product product;
    private int count;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int number) {
        this.count = number;
    }
}
