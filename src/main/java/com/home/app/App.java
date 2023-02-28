package com.home.app;

import com.home.app.matcher.AllFileMatcher;
import com.home.app.matcher.FileMatcher;
import com.home.app.matcher.GifFileMatcher;
import com.home.app.matcher.JpegFileMatcher;
import com.home.app.matcher.JpgFileMatcher;
import com.home.app.matcher.Mp4FileMatcher;
import com.home.app.matcher.PngFileMatcher;
import com.home.app.transformation.FileTransformation;
import com.home.app.transformation.Transformation42;
import com.home.app.transformation.Transformation86;
import com.home.app.transformation.Transformation86Bracket;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static List<File> listFiles(String dir, FileMatcher fileMather) {
        var files = new ArrayList<File>();
        var queue = new LinkedList<File>();
        queue.add(new File(dir));

        while (!queue.isEmpty()) {
            var currentDir = queue.poll();
            var dirs = Stream.of(currentDir.listFiles())
                    .filter(file -> file.isDirectory())
                    .collect(Collectors.toList());
            queue.addAll(dirs);
            var currentDirFiles = Stream.of(currentDir.listFiles())
                    .filter(file -> !file.isDirectory() && fileMather.isMatch(file))
                    .collect(Collectors.toList());
            files.addAll(currentDirFiles);
        }

        return files;
    }

    private static void printAll(String dir, FileMatcher mather) {
        var files = listFiles(dir, mather);

        var distinctFiles = new HashSet<String>();

        for (var file : files) {
//                System.out.println(file.getName());
            distinctFiles.add(file.getName());
        }

        System.out.print(mather.getClass());
        System.out.print(" ");
        System.out.print(files.size());
        System.out.print(" -> ");
        System.out.println(distinctFiles.size());
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        var dir = "/home/kirill/Yandex/Photos";
//        var pattern = "WP_\\d{6}_\\d{2}_\\d{2}_\\d{2}_Pro\\.jpg";
//        var pattern = "WP_20170411_17_44_48_Pro.jpg";
//        var pattern = "WP_\\d{6}_\\d{3}\\.jpg";
//        var pattern = "WP_20150818_001.jpg

        var mathers = new ArrayList<FileMatcher>();
        mathers.add(new AllFileMatcher());
        mathers.add(new JpgFileMatcher());
        mathers.add(new JpegFileMatcher());
        mathers.add(new PngFileMatcher());
        mathers.add(new Mp4FileMatcher());
        mathers.add(new GifFileMatcher());

        for (var m : mathers) {
            printAll(dir, m);
        }

        var transformations = new ArrayList<FileTransformation>();
        transformations.add(new Transformation86());
        transformations.add(new Transformation86Bracket());
        transformations.add(new Transformation42());

        for (var t : transformations) {
            printAll(dir, t);
        }

//        for (var s : transformations) {
//            try {
//                FileTime creationTime = (FileTime) Files.getAttribute(file.toPath(), "creationTime");
//                System.out.println(creationTime);
//            } catch (IOException ex) {
//                // handle exception
//                System.out.println("Oops!");
//            }
//        }
    }
}
