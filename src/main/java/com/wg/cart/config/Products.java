package com.wg.cart.config;

import java.util.HashMap;
import java.util.Map;

public class Products {

    public final static String IPAD="ipad";
    public final static String IPHONE="iphone";
    public final static String DISPLAYER = "显示器";
    public final static String LAPTOP="笔记本电脑";
    public final static String KEYBOARD="键盘";


    public final static String BREAD="面包";
    public final static String COOKIE="饼干";
    public final static String CAKE="蛋糕";
    public final static String BEEF="牛肉";
    public final static String FISH="鱼";
    public final static String VEGETABLES="蔬菜";


    public final static String NAPKIN="餐巾纸";
    public final static String BOX="收纳箱";
    public final static String COFFEECUP="咖啡杯";
    public final static String UMBRELLA="雨伞";

    public final static String BEER="啤酒";
    public final static String LIQUEUR="白酒";
    public final static String VODKA="伏特加";

    public final static Map<String,Category> products = new HashMap<>();

    static{
        products.put(IPAD,Category.ELECTRONIC);
        products.put(IPHONE,Category.ELECTRONIC);
        products.put(DISPLAYER,Category.ELECTRONIC);
        products.put(LAPTOP,Category.ELECTRONIC);
        products.put(KEYBOARD,Category.ELECTRONIC);

        products.put(BREAD,Category.FOOD);
        products.put(COOKIE,Category.FOOD);
        products.put(CAKE,Category.FOOD);
        products.put(BEEF,Category.FOOD);
        products.put(FISH,Category.FOOD);
        products.put(VEGETABLES,Category.FOOD);

        products.put(NAPKIN,Category.DAILYSTUFF);
        products.put(BOX,Category.DAILYSTUFF);
        products.put(COFFEECUP,Category.DAILYSTUFF);
        products.put(UMBRELLA,Category.DAILYSTUFF);

        products.put(BEER,Category.WINE);
        products.put(LIQUEUR,Category.WINE);
        products.put(VODKA,Category.WINE);
    }
}
