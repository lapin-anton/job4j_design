package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<String>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File f = file.toFile();
        FileProperty fileProperty = new FileProperty(f.length(), f.getName());
        List<String> paths;
        if (files.containsKey(fileProperty)) {
            paths = files.get(fileProperty);
        } else {
            paths = new ArrayList<>();
        }
        paths.add(f.getAbsolutePath());
        files.put(fileProperty, paths);
        return super.visitFile(file, attrs);
    }

    public List<String> getDuplicates() {
        return files.values().stream()
                .filter(paths -> paths.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
