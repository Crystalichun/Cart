package com.wg.cart.models;

import java.time.LocalDate;

public class Coupon {
    private LocalDate validDate;
    private int miniAmount;
    private int discountAmount;
    private boolean isValid;


    public LocalDate getValidDate() {
        return validDate;
    }

    public void setValidDate(LocalDate validDate) {
        this.validDate = validDate;
    }

    public int getMiniAmount() {
        return miniAmount;
    }

    public void setMiniAmount(int miniAmount) {
        this.miniAmount = miniAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
