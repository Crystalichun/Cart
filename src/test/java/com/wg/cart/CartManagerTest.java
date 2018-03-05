package com.wg.cart;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CartManagerTest {

    private String filePath = "orders.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() throws IOException {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void destroy() throws IOException {
        if(Files.exists(Paths.get(filePath))) {
            Files.delete(Paths.get(filePath));
        }
        System.setOut(System.out);
    }

    @Test
    public void testNormalOutPut1() throws IOException {
        createNormalTestFile1();
        String expectedOutPut = "3083.60" +System.lineSeparator();
        CartManager.main(null);
        Assert.assertEquals(expectedOutPut,outContent.toString());
    }

    @Test
    public void testNormalOutPut2() throws IOException {
        createNormalTestFile2();
        String expectedOutPut = "43.54" +System.lineSeparator();
        CartManager.main(null);
        Assert.assertEquals(expectedOutPut,outContent.toString());
    }

    @Test
    public void testFileNotExist() throws IOException {
        String expectedOutPut = "file is not exist" + System.lineSeparator();
        CartManager.main(null);
        Assert.assertEquals(expectedOutPut,outContent.toString());
    }

    @Test
    public void testFileIsEmpty() throws IOException {
        String expectedOutPut = "file is empty"+ System.lineSeparator();
        clearFile();
        CartManager.main(null);
        Assert.assertEquals(expectedOutPut,outContent.toString());
    }

    private void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filePath);
        writer.print("");
        writer.close();
    }

    @Test
    public void testProductIsNotFound() throws IOException {
        createNoSuchProductFile();
        String expectedOutPut = "no such products -> 苹果" +System.lineSeparator();
        CartManager.main(null);
        Assert.assertEquals(expectedOutPut,outContent.toString());
    }

    @Test
    public void testDateIsError() throws IOException {
        createErrorDateFile();
        String expectedOutPut = "date format error" +System.lineSeparator();
        CartManager.main(null);
        Assert.assertEquals(expectedOutPut,outContent.toString());
    }

    @Test
    public void testAccountDateIsError() throws IOException {
        createErrorAccountDateFile();
        String expectedOutPut = "account date is missing or error" +System.lineSeparator();
        CartManager.main(null);
        Assert.assertEquals(expectedOutPut,outContent.toString());
    }

    @Test
    public void testFileFormatError() throws IOException {
        createErrorFormatFile();
        String expectedOutPut = "account date is missing or error" +System.lineSeparator();
        CartManager.main(null);
        Assert.assertEquals(expectedOutPut,outContent.toString());
    }

    private void createErrorFormatFile() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
            writer.write("2013.1111.11|0.7|电子aa");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("");
            writer.write("1*ipad : 2399.00");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("201111.11.11");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("2014.3.2 1000 200");
        }
    }

    private void createErrorAccountDateFile() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
            writer.write("2013.11.11|0.7|电子");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("1*ipad : 2399.00");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("201113.11.11");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("2014.3.2 1000 200");
        }
    }


    private void createErrorDateFile() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
            writer.write("2013.11233.11333|0.7|电子");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("1*ipad : 2399.00");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("2013.11.11");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("2014.3.222 1000 200");
        }
    }

    private void createNoSuchProductFile() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
            writer.write("2013.11.11|0.7|电子");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("1*ipad : 2399.00");
            writer.newLine();
            writer.write("1* 苹果 : 1799.00");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("2013.11.11");
            writer.newLine();
            writer.write("");
        }
    }

    private void createNormalTestFile1() throws IOException {
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

    private void createNormalTestFile2() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
            writer.write("");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("3*蔬菜 : 5.98");
            writer.newLine();
            writer.write("8* 餐巾纸 : 3.20");
            writer.newLine();
            writer.write("");
            writer.newLine();
            writer.write("2013.11.11");
            writer.newLine();
            writer.write("");
        }
    }


}
