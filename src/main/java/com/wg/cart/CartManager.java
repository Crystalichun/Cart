package com.wg.cart;

import com.wg.cart.exceptions.AccountDateMissingException;
import com.wg.cart.exceptions.DateFormatException;
import com.wg.cart.exceptions.NoSuchProductException;
import com.wg.cart.models.Cart;
import com.wg.cart.parsers.CartParser;
import com.wg.cart.utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class CartManager {
    public static void main(String[] args){
        String file = "orders.txt";
        try {
            FileUtils.checkOrderFile(file);
            Cart cart = CartParser.parse(file);
            System.out.println(cart.getTotalFee());
        } catch (FileNotFoundException e) {
            System.out.println("file is not exist");
        } catch (NoSuchElementException e){
            System.out.println("file is empty");
        } catch (NoSuchProductException e) {
            System.out.println("no such products -> " + e.getMessage());
        } catch (DateFormatException e) {
            System.out.println("date format error");
        } catch (AccountDateMissingException e) {
            System.out.println("account date is missing or error");
        } catch (Exception e){
            System.out.println("your file format is error");
        }

    }
}
