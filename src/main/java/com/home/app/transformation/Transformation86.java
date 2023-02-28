package com.home.app.transformation;

import java.io.File;

public class Transformation86 extends FileTransformation {

    public Transformation86() {
        super("\\d{8}_\\d{6}\\..*");
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
