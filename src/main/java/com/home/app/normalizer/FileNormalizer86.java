package com.home.app.normalizer;

import java.io.File;

public class FileNormalizer86 extends FileNormalizer {

    public FileNormalizer86() {
        super("\\d{8}_\\d{6}");
    }
    
    @Override
    public String getNewName(File file) {
        var oldName = file.getName().toLowerCase();
        
        var newName = new StringBuilder();

        newName.append(oldName.substring(0, 8));
        newName.append(oldName.substring(9));

        return newName.toString();
    }
}
