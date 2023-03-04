package com.home.app;

import com.home.app.normalizer.FileNormalizerWp82;
import java.io.File;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileNormalizerWp82Test {

    private FileNormalizerWp82 normalizer;

    @BeforeEach
    void setUp() throws Exception {
        normalizer = new FileNormalizerWp82();
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
        var expected = LocalDateTime.of(2016, 5, 12, 13, 21, 2);
        var actual = normalizer.getTimestamp(new File("WP_20160512_13_21_02_Pro.jpg"));
        assertEquals(expected, actual);
    }
}