package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Dir {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\admin\\Desktop\\GU");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s", file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsolutePath()));
        }
        System.out.println(String.format("size: %s", file.getTotalSpace()));
        for (File subfile: file.listFiles()) {
            long size;
            if (subfile.isDirectory()) {
                CalculateSize calculateSize = new CalculateSize();
                Files.walkFileTree(subfile.toPath(), calculateSize);
                size = calculateSize.getSize();
            } else {
                size = file.length();
            }
            System.out.printf("name: %s, size: %s%n", subfile.getName(), size);
        }
    }
}
