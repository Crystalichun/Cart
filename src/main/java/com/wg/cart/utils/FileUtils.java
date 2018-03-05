package com.wg.cart.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class FileUtils {
     public static void checkOrderFile(String orderfilePath) throws NoSuchElementException, FileNotFoundException {
            File file = new File(orderfilePath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            if (file.length() == 0) {
                throw new NoSuchElementException();
            }

    }
}
