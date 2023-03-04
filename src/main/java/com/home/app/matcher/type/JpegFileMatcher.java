package com.home.app.matcher.type;

import com.home.app.matcher.FileMatcher;

public class JpegFileMatcher extends FileMatcher {

    public JpegFileMatcher() {
        this(null);
    }

    public JpegFileMatcher(FileMatcher matcher) {
        super(".*\\.jpeg", matcher);
    }
}
