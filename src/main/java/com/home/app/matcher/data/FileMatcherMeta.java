package com.home.app.matcher.data;

import com.home.app.matcher.FileMatcher;

public class FileMatcherMeta extends FileMatcher {

    public FileMatcherMeta() {
        super(null);
    }

    public FileMatcherMeta(FileMatcher matcher) {
        super(".*\\.(jpg|jpeg|png|mp4|gif)", matcher);
    }
}
