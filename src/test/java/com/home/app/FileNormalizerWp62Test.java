package com.home.app;

import com.home.app.normalizer.FileNormalizerWp62;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileNormalizerWp62Test {

    private FileNormalizerWp62 normalizer;

    @BeforeEach
    void setUp() throws Exception {
        normalizer = new FileNormalizerWp62();
    }

    @Test
    @DisplayName("File is matched")
    void testIsMatchTrue() {
        assertEquals(true, normalizer.isMatch(new File("WP_20160512_13_21_02_Pro.jpg")));
    }
    
    @Test
    @DisplayName("File is not matched")
    void testIsMatchFalse() {
        assertEquals(false, normalizer.isMatch(new File("20160512_13_21_02_Pro.jpg")));
    }
    
    @Test
    @DisplayName("Correct normalized name")
    void testGetNewNameTrue() {
        assertEquals("20160512132102.jpg", normalizer.getNewName(new File("WP_20160512_13_21_02_Pro.jpg")));
    }
}