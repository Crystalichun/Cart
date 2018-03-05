package com.wg.cart.parsers;

import com.wg.cart.exceptions.AccountDateMissingException;
import com.wg.cart.exceptions.DateFormatException;
import com.wg.cart.models.Cart;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class CartParserTest {
    private String filePath = "order.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
            writer.write("2013.11.11|0.7|电子");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("1*ipad : 2399.00");
            writer.newLine();
            writer.write("1* 显示器 : 1799.00");
            writer.newLine();
            writer.write("12*啤酒 : 25.00");
            writer.newLine();
            writer.write("5*面包 : 9.00");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("2013.11.11");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("2014.3.2 1000 200");
        }
    }

    @After
    public void destroy() throws IOException {
        Files.delete(Paths.get(filePath));
    }

    @Test
    public void testParse() throws DateFormatException, AccountDateMissingException {
        Cart cart = CartParser.parse(filePath);

        Assert.assertEquals(LocalDate.of(2013,11,11),cart.getPromotions().get(0).getPromotionDate());
        Assert.assertEquals((Double) 0.7,cart.getPromotions().get(0).getDiscount());
        Assert.assertEquals("电子",cart.getPromotions().get(0).getCategory().value);
        Assert.assertEquals((int)1,cart.getOrders().get(0).getCount());
        Assert.assertEquals("ipad",cart.getOrders().get(0).getProduct().getName());
        Assert.assertEquals((Double)2399.00,cart.getOrders().get(0).getProduct().getPrice());
        Assert.assertEquals((int)1,cart.getOrders().get(1).getCount());
        Assert.assertEquals("显示器",cart.getOrders().get(1).getProduct().getName());
        Assert.assertEquals((Double)1799.00,cart.getOrders().get(1).getProduct().getPrice());
        Assert.assertEquals(LocalDate.of(2013,11,11),cart.getAccountDate());
        Assert.assertEquals(LocalDate.of(2014,3,2),cart.getCoupon().getValidDate());
        Assert.assertEquals((int)1000,cart.getCoupon().getMiniAmount());
        Assert.assertEquals((int)200,cart.getCoupon().getDiscountAmount());
    }

}