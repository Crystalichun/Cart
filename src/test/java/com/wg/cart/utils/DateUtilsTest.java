package com.wg.cart.utils;

import com.wg.cart.exceptions.DateFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void testCreateLocalDateSuccess() throws DateFormatException {
        int year = 2011;
        int month =12;
        int day = 11;
        Assert.assertEquals(LocalDate.of(2011,12,11),DateUtils.createLocalDate(year,month,day));
    }

    @Test(expected=DateFormatException.class)
    public void testCreateLocalDateError() throws Exception, DateFormatException {
        int year =132334;
        int month =1212;
        int day = 121;
        DateUtils.createLocalDate(year,month,day);
    }

}