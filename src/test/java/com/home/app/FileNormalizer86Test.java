package com.home.app;

import com.home.app.normalizer.FileNormalizer86;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileNormalizer86Test {

    private FileNormalizer86 normalizer;

    @BeforeEach
    void setUp() throws Exception {
        normalizer = new FileNormalizer86();
    }

    @Test
    @DisplayName("File is matched - jpg")
    void testIsMatchTrueJpg() {
        assertEquals(true, normalizer.isMatch(new File("20220330_145310.jpg")));
    }
    
    @Test
    @DisplayName("File is matched - jpeg")
    void testIsMatchTrueJpeg() {
        assertEquals(true, normalizer.isMatch(new File("20220330_145310.jpeg")));
    }
    
    @Test
    @DisplayName("File is matched - png")
    void testIsMatchTruePng() {
        assertEquals(true, normalizer.isMatch(new File("20220330_145310.png")));
    }
    
    @Test
    @DisplayName("File is not matched")
    void testIsMatchFalse() {
        assertEquals(false, normalizer.isMatch(new File("2022-03-30_145310.jpg")));
    }
    
    @Test
    @DisplayName("Correct normalized name - jpg")
    void testGetNewNameTrueJpg() {
        assertEquals("20220330145310.jpg", normalizer.getNewName(new File("20220330_145310.jpg")));
    }
    
    @Test
    @DisplayName("Correct normalized name - jpeg")
    void testGetNewNameTrueJpeg() {
        assertEquals("20220330145310.jpeg", normalizer.getNewName(new File("20220330_145310.jpeg")));
    }
    
    @Test
    @DisplayName("Correct normalized name - png")
    void testGetNewNameTruePng() {
        assertEquals("20220330145310.png", normalizer.getNewName(new File("20220330_145310.png")));
    }
}