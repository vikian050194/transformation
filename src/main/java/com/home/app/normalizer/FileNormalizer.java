package com.home.app.normalizer;

import com.home.app.matcher.FileMatcher;
import java.io.File;

public abstract class FileNormalizer extends FileMatcher {

    public FileNormalizer(String pattern) {
        super(pattern + "\\..*");
    }

    public abstract String getNewName(File file);
}
