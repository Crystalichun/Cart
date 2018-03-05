package com.wg.cart.config;


public enum Category {
    ELECTRONIC("电子"), FOOD("食品"), DAILYSTUFF("日用品"), WINE("酒类");

    public final String value;

    Category(String value) {
        this.value = value;
    }

    public static Category getCategory(String value){
        for(Category category : Category.values()){
            if(value.equals(category.value)){
                return category;
            }
        }
        return null;
    }
}


