package com.wg.cart.exceptions;

public class NoSuchProductException extends Throwable {
    public NoSuchProductException(String name) {
        super(name);
    }
}
