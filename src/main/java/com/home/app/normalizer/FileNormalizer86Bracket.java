package com.home.app.normalizer;

import com.home.app.matcher.FileMatcher;
import com.home.app.matcher.data.FileMatcher86Brackets;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileNormalizer86Bracket extends FileMatcher86Brackets implements FileNormalizer {

    public FileNormalizer86Bracket() {
        this(null);
    }

    public FileNormalizer86Bracket(FileMatcher matcher) {
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
