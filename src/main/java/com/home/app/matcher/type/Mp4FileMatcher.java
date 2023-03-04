package com.home.app.matcher.type;

import com.home.app.matcher.FileMatcher;

public class Mp4FileMatcher extends FileMatcher {

    public Mp4FileMatcher() {
        this(null);
    }

    public Mp4FileMatcher(FileMatcher matcher) {
        super(".*\\.mp4", matcher);
    }
}
