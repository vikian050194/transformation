package com.home.app.normalizer;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileNormalizer86Bracket extends FileNormalizer {

    public FileNormalizer86Bracket() {
        super("\\d{8}_\\d{6}\\(\\d*\\)\\..*");
    }

    @Override
    public LocalDateTime getTimestamp(File file) {
        var oldName = file.getName().toLowerCase();
        oldName = oldName.substring(0, 15);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(oldName, formatter);

        return dateTime;
    }
}
