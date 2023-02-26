package com.home.app;

import com.home.app.transformation.FileTransformation;
import com.home.app.transformation.EightSix;
import com.home.app.matcher.AllFileMatcher;
import com.home.app.matcher.JpgFileMatcher;
import com.home.app.matcher.JpegFileMatcher;
import com.home.app.matcher.PngFileMatcher;
import com.home.app.matcher.FileMatcher;
import com.home.app.matcher.GifFileMatcher;
import com.home.app.matcher.Mp4FileMatcher;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static List<File> listFiles(String dir, FileMatcher fileMather) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory() && fileMather.isMatch(file))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        var dir = "/home/kirill/Yandex/Photos";
//        var pattern = "\\d{8}_\\d{6}\\.jpg";
//        var pattern = "\\d{8}_\\d{6}(\\(\\d\\))?\\.jpg";
//        var pattern = "WP_\\d{6}_\\d{2}_\\d{2}_\\d{2}_Pro\\.jpg";
//        var pattern = "WP_20170411_17_44_48_Pro.jpg";
//        var pattern = "WP_\\d{6}_\\d{3}\\.jpg";
//        var pattern = "WP_20150818_001.jpg

        var sizers = new ArrayList<FileMatcher>();
        sizers.add(new AllFileMatcher());
        sizers.add(new JpgFileMatcher());
        sizers.add(new JpegFileMatcher());
        sizers.add(new PngFileMatcher());
        sizers.add(new Mp4FileMatcher());
        sizers.add(new GifFileMatcher());

        for (var s : sizers) {
            var files = listFiles(dir, s);

            for(var file: files){
                System.out.println(file.getName());
            }

            System.out.print(s.getClass());
            System.out.print(" ");
            System.out.println(files.size());
        }
        
//        var transformations = new ArrayList<FileTransformation>();
//        transformations.add(new EightSix());
//
//        for (var t : transformations) {
//            var files = listFiles(dir, t);
//            System.out.println(files.size());
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
