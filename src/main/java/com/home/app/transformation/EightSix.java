package com.home.app.transformation;

import java.io.File;

public class EightSix extends FileTransformation {

    public EightSix() {
        super("\\d{8}_\\d{6}\\.jpg");
    }

    @Override
    public String getNewName(File file) {
        var oldName = file.getName();

        var newName = new StringBuilder();

        newName.append(oldName.substring(0, 4));
        newName.append("-");
        newName.append(oldName.substring(4, 6));
        newName.append("-");
        newName.append(oldName.substring(6, 8));

        newName.append(" ");

        newName.append(oldName.substring(9, 11));
        newName.append("-");
        newName.append(oldName.substring(11, 13));
        newName.append("-");
        newName.append(oldName.substring(13, 15));

        newName.append(oldName.substring(15));

        return newName.toString();
    }
}
