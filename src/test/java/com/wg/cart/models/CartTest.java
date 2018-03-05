package com.wg.cart.models;

import com.wg.cart.config.Category;
import com.wg.cart.exceptions.NoSuchProductException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Order.class,Category.class,Product.class,Promotion.class,Coupon.class,NoSuchProductException.class})
public class CartTest {
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void testGetTotalFee() throws NoSuchProductException {
        Promotion promotion = mock(Promotion.class);
        this.cart.setPromotions(new ArrayList<Promotion>());
        this.cart.getPromotions().add(promotion);
        this.cart.setOrders(new ArrayList<Order>());
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        this.cart.getOrders().add(order1);
        this.cart.getOrders().add(order2);
        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        LocalDate mockDate = LocalDate.of(2012,12,12);
        this.cart.setAccountDate(mockDate);
        Coupon coupon = mock(Coupon.class);
        this.cart.setCoupon(coupon);
        LocalDate validDate =LocalDate.of(2013,3,3);
        coupon.setValidDate(validDate);

        PowerMockito.when(promotion.getCategory()).thenReturn(category1);
        PowerMockito.when(promotion.getPromotionDate()).thenReturn(mockDate);
        PowerMockito.when(promotion.getDiscount()).thenReturn(0.5);
        PowerMockito.when(order1.getProduct()).thenReturn(product1);
        PowerMockito.when(order2.getProduct()).thenReturn(product2);
        PowerMockito.when(product1.getCategory()).thenReturn(category1);
        PowerMockito.when(product2.getCategory()).thenReturn(category2);
        PowerMockito.when(order1.getCount()).thenReturn(1);
        PowerMockito.when(order2.getCount()).thenReturn(1);
        PowerMockito.when(order1.getProduct().getPrice()).thenReturn((double) 2000.00);
        PowerMockito.when(order2.getProduct().getPrice()).thenReturn((double) 1000.00);
        PowerMockito.when(coupon.isValid()).thenReturn(true);
        PowerMockito.when(coupon.getMiniAmount()).thenReturn(1000);
        PowerMockito.when(coupon.getDiscountAmount()).thenReturn(200);
        PowerMockito.when(coupon.getValidDate()).thenReturn(validDate);

        Assert.assertEquals("1800.00",cart.getTotalFee());
    }

    @Test(expected=NoSuchProductException.class)
    public void testNoSuchProductException() throws NoSuchProductException {
        this.cart.setOrders(new ArrayList<Order>());
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        this.cart.getOrders().add(order1);
        this.cart.getOrders().add(order2);
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);

        PowerMockito.when(order1.getProduct()).thenReturn(product1);
        PowerMockito.when(order2.getProduct()).thenReturn(product2);
        PowerMockito.when(product1.getCategory()).thenReturn(null);
        PowerMockito.when(product2.getCategory()).thenReturn(null);

        cart.getTotalFee();
    }

}