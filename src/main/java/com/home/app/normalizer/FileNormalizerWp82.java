package com.home.app.normalizer;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileNormalizerWp82 extends FileNormalizer {

    public FileNormalizerWp82() {
        super("wp_\\d{8}_\\d{2}_\\d{2}_\\d{2}_pro\\..*");
    }
    
    @Override
    public LocalDateTime getTimestamp(File file) {
        var oldName = file.getName().toLowerCase();
        oldName = oldName.substring(3, 20);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH_mm_ss");
        LocalDateTime dateTime = LocalDateTime.parse(oldName, formatter);

        return dateTime;
    }
}
