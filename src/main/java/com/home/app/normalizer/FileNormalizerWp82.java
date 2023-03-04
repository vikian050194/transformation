package com.home.app.normalizer;

import com.home.app.matcher.FileMatcher;
import com.home.app.matcher.data.FileMatcherWp82;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileNormalizerWp82 extends FileMatcherWp82 implements FileNormalizer {

    public FileNormalizerWp82() {
        this(null);
    }

    public FileNormalizerWp82(FileMatcher matcher) {
        super(matcher);
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
