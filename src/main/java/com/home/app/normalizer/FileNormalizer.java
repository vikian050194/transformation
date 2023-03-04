package com.home.app.normalizer;

import com.home.app.matcher.FileMatcher;
import java.io.File;
import java.time.LocalDateTime;

public abstract class FileNormalizer extends FileMatcher {

    public FileNormalizer(String pattern) {
        super(pattern);
    }

    public abstract LocalDateTime getTimestamp(File file);
}
