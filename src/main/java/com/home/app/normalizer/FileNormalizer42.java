package com.home.app.normalizer;

import java.io.File;

public class FileNormalizer42 extends FileNormalizer {

    public FileNormalizer42() {
        super("\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}");
    }
    
    @Override
    public String getNewName(File file) {
        var oldName = file.getName().toLowerCase();
        
        var newName = new StringBuilder();

        newName.append(oldName.substring(0, 4));
        newName.append(oldName.substring(5, 7));
        newName.append(oldName.substring(8, 10));
        newName.append(oldName.substring(11, 13));
        newName.append(oldName.substring(14, 16));
        newName.append(oldName.substring(17));

        return newName.toString();
    }
}
