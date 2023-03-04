package com.home.app.normalizer;

import java.io.File;
import java.time.LocalDateTime;

public interface FileNormalizer {

    public LocalDateTime getTimestamp(File file);
}
