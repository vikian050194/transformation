package com.home.app.normalizer.meta;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.home.app.matcher.FileMatcher;
import com.home.app.matcher.data.FileMatcherMeta;
import com.home.app.normalizer.FileNormalizer;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileNormalizerMeta extends FileMatcherMeta implements FileNormalizer {

    public FileNormalizerMeta() {
        this(null);
    }

    public FileNormalizerMeta(FileMatcher matcher) {
        super(matcher);
    }

    @Override
    public LocalDateTime getTimestamp(File file) {
        Metadata metadata = new Metadata();

        try {
            metadata = ImageMetadataReader.readMetadata(file);
        } catch (ImageProcessingException | IOException ex) {
            Logger.getLogger(FileNormalizerMeta.class.getName()).log(Level.SEVERE, null, ex);
        }

        var directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

        if (directory == null) {
            return null;
        }

        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

        if (date == null) {
            return null;
        }

        var localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        return localDateTime;
    }
}
