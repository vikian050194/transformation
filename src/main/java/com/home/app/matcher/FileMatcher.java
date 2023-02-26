package com.home.app.matcher;

import java.io.File;
import java.util.regex.Pattern;

public class FileMatcher {

    private final Pattern pattern;
    
    public FileMatcher(String pattern) {
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }
    
    public boolean isMatch(File file){
        return pattern.matcher(file.getName()).matches();
    }
}
