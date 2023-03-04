package com.home.app.matcher.data;

import com.home.app.matcher.FileMatcher;

public class FileMatcher86All extends FileMatcher {

    public FileMatcher86All() {
        this(null);
    }

    public FileMatcher86All(FileMatcher matcher) {
        super("\\d{8}_\\d{6}.*", matcher);
    }
}
