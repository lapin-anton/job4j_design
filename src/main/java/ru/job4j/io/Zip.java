package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path: sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                    zip.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String pathFormat = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?";
        String extFormat = "^\\.\\w+$";
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        validateParam(pathFormat, "-d", directory);
        validateParam(extFormat, "-e", exclude);
        validateParam(pathFormat, "-o", output);
        List<Path> paths = Search.search(Paths.get(directory), p -> !p.toFile().getName().endsWith(exclude));
        Zip zip = new Zip();
        zip.packFiles(paths, new File(output));
    }

    private static void validateParam(String format, String paramName, String param) {
        if (!Pattern.matches(format, param)) {
            throw new IllegalArgumentException(String.format("Param %s has incorrect format", paramName));
        }
    }
}
