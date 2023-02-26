package com.home.app.transformation;

import com.home.app.matcher.FileMatcher;
import java.io.File;

public abstract class FileTransformation extends FileMatcher{

    public FileTransformation(String pattern) {
        super(pattern);
    }
    
    public abstract String getNewName(File file);
}
