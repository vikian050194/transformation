package com.home.app.normalizer;

import java.io.File;

public class FileNormalizerWp62 extends FileNormalizer {

    public FileNormalizerWp62() {
        super("wp_\\d{8}_\\d{2}_\\d{2}_\\d{2}_pro");
    }
    
    @Override
    public String getNewName(File file) {
        var oldName = file.getName().toLowerCase();
        
        var newName = new StringBuilder();

        newName.append(oldName.substring(3, 11));
        newName.append(oldName.substring(12, 14));
        newName.append(oldName.substring(15, 17));
        newName.append(oldName.substring(18, 20));
        newName.append(oldName.substring(24));

        return newName.toString();
    }
}
