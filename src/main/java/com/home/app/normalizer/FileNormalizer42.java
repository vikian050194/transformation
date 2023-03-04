package com.home.app.normalizer;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileNormalizer42 extends FileNormalizer {

    public FileNormalizer42() {
        super("\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}\\..*");
    }
    
    @Override
    public LocalDateTime getTimestamp(File file) {
        var oldName = file.getName().toLowerCase();
        oldName = oldName.substring(0, 19);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime dateTime = LocalDateTime.parse(oldName, formatter);

        return dateTime;
    }
}
