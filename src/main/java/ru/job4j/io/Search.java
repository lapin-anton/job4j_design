package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {
        String extFormat = "^\\.\\w+$";
        if (args.length < 2) {
            throw new IllegalArgumentException("Amount of input args is less than 2. "
                    + "Usage java -jar search.jar ROOT_FOLDER FILE_EXTENSION.");
        }
        if (!Pattern.matches(extFormat, args[1])) {
            throw new IllegalArgumentException(String.format("Argument '%s' has incorrect file extension format", args[1]));
        }
    }
}
