package com.home.app.matcher.type;

import com.home.app.matcher.FileMatcher;

public class JpgFileMatcher extends FileMatcher {

    public JpgFileMatcher() {
        this(null);
    }

    public JpgFileMatcher(FileMatcher matcher) {
        super(".*\\.jpg", matcher);
    }
}
