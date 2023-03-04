package com.home.app.matcher;

public class AllFileMatcher extends FileMatcher {

    public AllFileMatcher() {
        this(null);
    }

    public AllFileMatcher(FileMatcher matcher) {
        super(".*\\..*", matcher);
    }
}
