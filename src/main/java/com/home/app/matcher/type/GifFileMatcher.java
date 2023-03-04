package com.home.app.matcher.type;

import com.home.app.matcher.FileMatcher;

public class GifFileMatcher extends FileMatcher {
    
    public GifFileMatcher() {
        this(null);
    }
    
    public GifFileMatcher(FileMatcher matcher) {
        super(".*\\.gif", matcher);
    }
}
