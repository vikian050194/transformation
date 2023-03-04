package com.home.app.matcher.type;

import com.home.app.matcher.FileMatcher;

public class PngFileMatcher extends FileMatcher {

    public PngFileMatcher() {
        this(null);
    }

    public PngFileMatcher(FileMatcher matcher) {
        super(".*\\.png", matcher);
    }
}
