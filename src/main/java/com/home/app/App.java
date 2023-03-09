package com.home.app;

import com.home.app.matcher.*;
import com.home.app.matcher.data.*;
import com.home.app.matcher.type.*;
import com.home.app.matcher.wrapper.InvertedFileMatcherWrapper;
import com.home.app.normalizer.*;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    private static final HashMap<String, Integer> distinctFiles = new HashMap<>();

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

        var fooFiles = new HashSet<String>();

        for (var file : files) {
            if (printFiles) {
                System.out.println(file.getName());
            }
            fooFiles.add(file.getName());
        }

        System.out.print(mather.getClass());
        System.out.print(" ");
        System.out.print(files.size());
        System.out.print(" -> ");
        System.out.println(fooFiles.size());
    }

    private static void transformAll(String dir, FileNormalizer transformator, boolean printFiles) {
        var files = listFiles(dir, (FileMatcher) transformator);
        var total = files.size();
        var counter = 0;

        for (var file : files) {
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            var fileExtension = oldName.split("\\.")[1];
            var pureName = fileTimestamp.format(formatter);
            var newName = pureName + "." + fileExtension;
            var oldFullName = file.getAbsolutePath();

            if (distinctFiles.containsKey(pureName)) {
                var index = distinctFiles.get(pureName);
                newName = pureName + "(" + index + ")" + "." + fileExtension;
                distinctFiles.put(pureName, index + 1);
            } else {
                distinctFiles.put(pureName, 0);
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
                System.out.print(oldName);
                System.out.print(" -> ");
                System.out.print(newName);
                System.out.print(" -> ");
                System.out.println("exception");
            }
        }

        System.out.print(counter);
        System.out.print("/");
        System.out.println(total);
        System.out.println(distinctFiles.size());
    }

    public static void main(String[] args) {
        var dir = args[0];

        var mathers = new ArrayList<FileMatcher>();

        mathers.add(new AllFileMatcher());
        mathers.add(new JpgFileMatcher());
        mathers.add(new JpegFileMatcher());
        mathers.add(new PngFileMatcher());
        mathers.add(new Mp4FileMatcher());
        mathers.add(new GifFileMatcher());
        mathers.add(new FileMatcher14());
        mathers.add(new FileMatcher42());
        mathers.add(new FileMatcher42All());
        mathers.add(new FileMatcher86());
        mathers.add(new FileMatcher86Brackets());
        mathers.add(new FileMatcher86All());
        mathers.add(new FileMatcherWp82());

        for (var m : mathers) {
            printAll(dir, m, false);
        }

        System.out.println();

        printAll(dir, new InvertedFileMatcherWrapper(new FileMatcher86All()), true);

        System.out.println();

        var transformations = new ArrayList<FileNormalizer>();
//        transformations.add(new FileNormalizer14());
//        transformations.add(new FileNormalizer42());
//        transformations.add(new FileNormalizer86All());
//        transformations.add(new FileNormalizer86());
//        transformations.add(new FileNormalizer86Bracket());
//        transformations.add(new FileNormalizer42All());
//        transformations.add(new FileNormalizerWp82());
//        transformations.add(new FileNormalizerWp82());
//        transformations.add(new FileNormalizerImg4Jpg());
//        transformations.add(new FileNormalizerMeta(new InlineFileMatcher("(?!skip).*", new InvertedFileMatcherWrapper(new FileMatcher42All()))));

        for (var t : transformations) {
            transformAll(dir, t, false);
        }
    }
}
