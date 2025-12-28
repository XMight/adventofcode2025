package com.abusmac.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class InputOutputUtils {
    public static String readResourceFileData(String fileName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        return Files.readString(path);
    }
}
