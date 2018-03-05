package com.wg.cart.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileUtils.class, File.class})
public class FileUtilsTest {

    private String filePath = "text.txt";
    @Mock
    private File mockFile;

    @Test
    public void testCheckFile() throws Exception {
        PowerMockito.whenNew(File.class).withArguments(filePath).thenReturn(mockFile);
        PowerMockito.when(mockFile.exists()).thenReturn(true);
        PowerMockito.when(mockFile.length()).thenReturn((long) 1);
        FileUtils.checkOrderFile(filePath);
    }

    @Test(expected=FileNotFoundException.class)
    public void testFileNotExist() throws Exception {
        PowerMockito.whenNew(File.class).withArguments(filePath).thenReturn(mockFile);
        PowerMockito.when(mockFile.exists()).thenReturn(false);
        FileUtils.checkOrderFile(filePath);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFileEmpty() throws Exception {
        PowerMockito.whenNew(File.class).withArguments(filePath).thenReturn(mockFile);
        PowerMockito.when(mockFile.exists()).thenReturn(true);
        PowerMockito.when(mockFile.length()).thenReturn((long) 0);
        FileUtils.checkOrderFile(filePath);
    }



}