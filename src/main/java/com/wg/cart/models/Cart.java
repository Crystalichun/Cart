package com.wg.cart.models;

import com.wg.cart.exceptions.NoSuchProductException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Cart {
    private Double totalFee = 0.0;
    private List<Order> orders;
    private List<Promotion> promotions;
    private LocalDate accountDate;
    private Coupon coupon;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void setAccountDate(LocalDate accountDate) {
        this.accountDate = accountDate;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() { return coupon; }

    public LocalDate getAccountDate() { return accountDate; }

    public String getTotalFee() throws NoSuchProductException {
        for (Order order : this.orders) {
            if (order.getProduct().getCategory() == null) {
                throw new NoSuchProductException(order.getProduct().getName());
            } else {
                totalFee += order.getCount() * getProductPrice(order.getProduct(), promotions);
            }
        }

        if (hasValidCoupon(totalFee)) {
            this.totalFee -= coupon.getDiscountAmount();
        }
        return new DecimalFormat("0.00").format(totalFee);
    }

    private boolean hasValidCoupon(double totalFee) {
        return (
                this.coupon != null
                        && this.coupon.isValid()
                        && totalFee >= coupon.getMiniAmount()
                        && coupon.getValidDate().isAfter(this.accountDate)
        );
    }

    private double getProductPrice(Product product, List<Promotion> promotions) {
        Optional<Promotion> promotion = promotions.stream().filter(p -> p.getCategory().equals(product.getCategory())).findFirst();
        if (promotion.isPresent() && promotion.get().getPromotionDate().isEqual(accountDate)) {
            return product.getPrice() * promotion.get().getDiscount();
        } else {
            return product.getPrice();
        }
    }
}
