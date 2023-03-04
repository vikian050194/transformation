package com.home.app.matcher.wrapper;

import com.home.app.matcher.FileMatcher;
import java.io.File;

public class InvertedFileMatcherWrapper extends FileMatcher {
    private final FileMatcher wrappedMatcher;
    
    
    public InvertedFileMatcherWrapper(FileMatcher matcher) {
        super(".*");
        this.wrappedMatcher = matcher;
    }
    
    @Override
    public boolean isMatch(File file) {
        return !wrappedMatcher.isMatch(file);
    }
}
