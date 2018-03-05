package com.wg.cart.parsers;

import com.wg.cart.config.Category;
import com.wg.cart.exceptions.AccountDateMissingException;
import com.wg.cart.exceptions.DateFormatException;
import com.wg.cart.models.*;
import com.wg.cart.utils.DateUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CartParser {
    private final static String PROMOTION_PATTERN = "^(\\d*).(\\d*).(\\d*)\\|(0.\\d*)\\|([\\u4E00-\\u9FA5]+)$";
    private final static String ORDER_PATTERN = "^(\\d+)\\s*\\*\\s*([\\u4e00-\\u9fa5_a-zA-Z0-9]+)\\s*:\\s*(\\d+(\\.\\d+)?)$";
    private final static String ACOUNT_DATE_PATTERN = "^(\\d{4}).(\\d{1}|\\d{2}).(\\d{1}|\\d{2})$";
    private final static String COUPON_PATTERN = "^(\\d*).(\\d*).(\\d*)\\s+(\\d+)\\s+(\\d+)$";
    private final static String[] FILECONTENT = new String[]{"PROMOTION", "ORDERS", "ACCOUNT_DATE", "COUPON"};
    private final static int VALID_MINI_AMOUNT=1000;
    private final static int VALID_DISCOUNT_AMOUNT=200;


    public static Cart parse(String file) throws DateFormatException, AccountDateMissingException {
        Cart cart = new Cart();
        cart.setOrders(new ArrayList<Order>());
        cart.setPromotions(new ArrayList<Promotion>());

        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            List<String> lines = stream.collect(Collectors.toList());
            int flagIndex = 0;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.equals("")) {
                    if (i != 0) {
                        flagIndex++;
                    }
                } else {
                    switch (FILECONTENT[flagIndex]) {
                        case "PROMOTION":
                            cart.getPromotions().add(parsePromotion(line));
                            break;
                        case "ORDERS":
                            cart.getOrders().add(parseOrder(line));
                            break;
                        case "ACCOUNT_DATE":
                            cart.setAccountDate(parseAccountDate(line));
                            break;
                        case "COUPON":
                            cart.setCoupon(parseCoupon(line));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cart;
    }

    private static Promotion parsePromotion(String line) throws DateFormatException {
        Pattern pattern = Pattern.compile(PROMOTION_PATTERN);
        Matcher matcher = pattern.matcher(line);
        Promotion promotion = new Promotion();
        if (matcher.find()) {
            LocalDate PromotionDate = DateUtils.createLocalDate(Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            promotion.setPromotionDate(PromotionDate);
            promotion.setDiscount(Double.parseDouble(matcher.group(4)));
            promotion.setCategory(Category.getCategory(matcher.group(5)));
        }
        return promotion;
    }

    private static Order parseOrder(String line) {
        Pattern pattern = Pattern.compile(ORDER_PATTERN);
        Matcher matcher = pattern.matcher(line);
        Order order = new Order();
        if (matcher.find()) {
            order.setCount(Integer.parseInt(matcher.group(1)));
            order.setProduct(new Product(matcher.group(2), Double.parseDouble(matcher.group(3))));
        }
        return order;
    }

    private static LocalDate parseAccountDate(String line) throws DateFormatException, AccountDateMissingException {
        Pattern pattern = Pattern.compile(ACOUNT_DATE_PATTERN);
        Matcher matcher = pattern.matcher(line);
        LocalDate accountDate = null;
        if (matcher.find()) {
            accountDate = DateUtils.createLocalDate(Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
        }
        if(accountDate==null){
            throw new AccountDateMissingException();
        }
        return accountDate;
    }

    private static Coupon parseCoupon(String line) throws DateFormatException {
        Pattern pattern = Pattern.compile(COUPON_PATTERN);
        Matcher matcher = pattern.matcher(line);
        Coupon coupon = new Coupon();
        if (matcher.find()) {
            LocalDate validDate = DateUtils.createLocalDate(Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            coupon.setValidDate(validDate);
            coupon.setMiniAmount(Integer.parseInt(matcher.group(4)));
            coupon.setDiscountAmount(Integer.parseInt(matcher.group(5)));
            coupon.setValid(coupon.getMiniAmount() == VALID_MINI_AMOUNT && coupon.getDiscountAmount() == VALID_DISCOUNT_AMOUNT);
        }
        return coupon;
    }
}
