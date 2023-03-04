package com.home.app;

import com.home.app.normalizer.FileNormalizer42;
import java.io.File;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileNormalizer42Test {

    private FileNormalizer42 normalizer;

    @BeforeEach
    void setUp() throws Exception {
        normalizer = new FileNormalizer42();
    }

    @Test
    @DisplayName("File is matched - jpg")
    void testIsMatchTrueJpg() {
        assertEquals(true, normalizer.isMatch(new File("2019-09-05 15-23-21.jpg")));
    }
    
    @Test
    @DisplayName("File is matched - jpeg")
    void testIsMatchTrueJpeg() {
        assertEquals(true, normalizer.isMatch(new File("2019-09-05 15-23-21.jpeg")));
    }
    
    @Test
    @DisplayName("File is matched - png")
    void testIsMatchTruePng() {
        assertEquals(true, normalizer.isMatch(new File("2019-09-05 15-23-21.png")));
    }
    
    @Test
    @DisplayName("File is not matched")
    void testIsMatchFalse() {
        assertEquals(false, normalizer.isMatch(new File("2019-09-05_15-23-21.jpg")));
    }
    
    @Test
    @DisplayName("Correct normalized name - jpg")
    void testGetNewNameTrueJpg() {
        var expected = LocalDateTime.of(2019, 9, 5, 15, 23, 21);
        var actual = normalizer.getTimestamp(new File("2019-09-05 15-23-21.jpg"));
        assertEquals(expected, actual);
    }
    
    @Test
    @DisplayName("Correct normalized name - jpeg")
    void testGetNewNameTrueJpeg() {
        var expected = LocalDateTime.of(2019, 9, 5, 15, 23, 21);
        var actual = normalizer.getTimestamp(new File("2019-09-05 15-23-21.jpeg"));
        assertEquals(expected, actual);
    }
    
    @Test
    @DisplayName("Correct normalized name - png")
    void testGetNewNameTruePng() {
        var expected = LocalDateTime.of(2019, 9, 5, 15, 23, 21);
        var actual = normalizer.getTimestamp(new File("2019-09-05 15-23-21.png"));
        assertEquals(expected, actual);
    }
}