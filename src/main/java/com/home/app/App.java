package com.home.app;

import com.home.app.matcher.AllFileMatcher;
import com.home.app.matcher.FileMatcher;
import com.home.app.matcher.GifFileMatcher;
import com.home.app.matcher.JpegFileMatcher;
import com.home.app.matcher.JpgFileMatcher;
import com.home.app.matcher.Mp4FileMatcher;
import com.home.app.matcher.PngFileMatcher;
import com.home.app.normalizer.FileNormalizer;
import com.home.app.normalizer.FileNormalizer42;
import com.home.app.normalizer.FileNormalizer86;
import com.home.app.normalizer.FileNormalizer86Bracket;
import com.home.app.normalizer.FileNormalizerImg4Jpg;
import com.home.app.normalizer.FileNormalizerWp82;
import com.home.app.normalizer.FileNormalizerMeta;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static void printAll(String dir, FileMatcher mather, boolean printFiles) {
        var files = listFiles(dir, mather);

        var distinctFiles = new HashSet<String>();

        for (var file : files) {
            if (printFiles) {
                System.out.println(file.getName());
            }
            distinctFiles.add(file.getName());
        }

        System.out.print(mather.getClass());
        System.out.print(" ");
        System.out.print(files.size());
        System.out.print(" -> ");
        System.out.println(distinctFiles.size());
    }

    private static void transformAll(String dir, FileNormalizer transformator, boolean printFiles) {
        var files = listFiles(dir, transformator);
        var total = files.size();
        var counter = 0;

        var distinctFiles = new HashMap<String, Integer>();

        FileMatcher fileMather = new AllFileMatcher();

        for (var file : files) {
            System.out.println(file.getName());

            if (!fileMather.isMatch(file)) {
                continue;
            }

            var oldName = file.getName().toLowerCase();
            var fileTimestamp = transformator.getTimestamp(file);
            if (fileTimestamp == null) {
                if (printFiles) {
                    System.out.print(oldName);
                    System.out.print(" -> ");
                    System.out.println("skipped");
                }
                continue;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
            var fileExtension = oldName.split("\\.")[1];
            var newName = fileTimestamp.format(formatter) + "." + fileExtension;
            var oldFullName = file.getAbsolutePath();

            if (distinctFiles.containsKey(newName)) {
                var index = distinctFiles.get(newName);
                newName = fileTimestamp.format(formatter) + "(" + index + ")" + "." + fileExtension;
                distinctFiles.replace(newName, index, index + 1);
            } else {
                distinctFiles.put(newName, 0);
            }

            if (printFiles) {
                System.out.print(oldName);
                System.out.print(" -> ");
                System.out.println(newName);
            }

            Path source = Paths.get(oldFullName);

            try {
                Files.move(source, source.resolveSibling(newName));
                counter++;
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.print(counter);
        System.out.print("/");
        System.out.println(total);
        System.out.println(distinctFiles.size());
    }

    public static void main(String[] args) {
        var dir = "/home/kirill/Yandex/Photos/Свадьба";

        var mathers = new ArrayList<FileMatcher>();

//        mathers.add(new AllFileMatcher());
        mathers.add(new JpgFileMatcher());
        mathers.add(new JpegFileMatcher());
        mathers.add(new PngFileMatcher());
        mathers.add(new Mp4FileMatcher());
        mathers.add(new GifFileMatcher());
        mathers.add(new FileNormalizer86());
        mathers.add(new FileNormalizer86Bracket());
        mathers.add(new FileNormalizer42());
        mathers.add(new FileNormalizerWp82());

        for (var m : mathers) {
            printAll(dir, m, false);
        }

        System.out.println();

        var transformations = new ArrayList<FileNormalizer>();
//        transformations.add(new FileNormalizer86());
//        transformations.add(new FileNormalizer86Bracket());
//        transformations.add(new FileNormalizer42());
//        transformations.add(new FileNormalizerWp82());
        transformations.add(new FileNormalizerMeta());
//        transformations.add(new FileNormalizerImg4Jpg());

        for (var t : transformations) {
            printAll(dir, new FileNormalizer42(), false);
            transformAll(dir, t, true);
            printAll(dir, new FileNormalizer42(), false);
        }
    }
}
