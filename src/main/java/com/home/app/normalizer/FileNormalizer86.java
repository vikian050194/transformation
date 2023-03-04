package com.home.app.normalizer;

import com.home.app.matcher.FileMatcher;
import com.home.app.matcher.data.FileMatcher86;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileNormalizer86 extends FileMatcher86 implements FileNormalizer {

    public FileNormalizer86() {
        this(null);
    }

    public FileNormalizer86(FileMatcher matcher) {
        super(matcher);
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
