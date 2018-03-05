package com.wg.cart.utils;

import com.wg.cart.exceptions.DateFormatException;

import java.time.LocalDate;

public class DateUtils {
    public static LocalDate createLocalDate(int year, int month, int day) throws DateFormatException {
       try{
           return LocalDate.of(year, month, day);
       } catch(Exception e){
           throw new DateFormatException();
       }
    }
}
