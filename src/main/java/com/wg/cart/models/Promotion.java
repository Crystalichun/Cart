package com.wg.cart.models;

import com.wg.cart.config.Category;

import java.time.LocalDate;

public class Promotion {
    private LocalDate promotionDate;
    private Double discount;
    private Category category;

    public LocalDate getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(LocalDate promotionDate) {
        this.promotionDate = promotionDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
