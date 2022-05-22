package ru.job4j.io.find;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileFinder {

    private static final Logger LOG = LoggerFactory.getLogger(FileFinder.class.getName());

    public static final String PATH_REGEX = "([a-zA-Z]:)?([\\\\\\/]?[a-zA-Z0-9_.-]+)+[\\\\\\/]?";
    public static final String MASK_REGEX = "^((.*\\*)|(\\*.*))$";
    public static final String NAME_REGEX = "^\\w+\\.\\w+$";
    public static List<String> criteria = List.of("mask", "name", "regex");

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String d = argsName.get("d");
        String n = argsName.get("n");
        String t = argsName.get("t");
        String o = argsName.get("o");
        validateArg("-d", d, s -> Pattern.matches(PATH_REGEX, s));
        validateArg("-t", t, criteria::contains);
        if ("mask".equals(t)) {
            validateArg("-n", n, s -> Pattern.matches(MASK_REGEX, s));
        } else if ("name".equals(t)) {
            validateArg("-n", n, s -> Pattern.matches(NAME_REGEX, s));
        }
        validateArg("-o", o, s -> Pattern.matches(PATH_REGEX, s));
        Predicate<Path> searchCondition = getPathPredicate(n, t);
        List<Path> files = Search.search(Paths.get(d), searchCondition);
        saveResult(files, o);
    }

    private static void saveResult(List<Path> files, String out) {
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            for (Path p: files) {
                pw.println(p.toString());
            }
            pw.flush();
        } catch (IOException e) {
            LOG.error("Error during result saving: ", e);
        }
    }

    private static Predicate<Path> getPathPredicate(String n, String t) {
        Predicate<Path> searchCondition = null;
        if ("mask".equals(t)) {
            String regex = n.replace(".", "\\.")
                    .replaceAll("\\*", ".*")
                    .replaceAll("\\?", "\\\\w");
            searchCondition = p -> Pattern.matches(regex, p.toFile().getName());
        }
        if ("name".equals(t)) {
            searchCondition = p -> p.toFile().getName().equals(n);
        }
        if ("regex".equals(t)) {
            searchCondition = p -> Pattern.matches(n, p.toFile().getName());
        }
        return searchCondition;
    }

    private static void validateArg(String argName, String arg, Predicate<String> condition) {
        if (!condition.test(arg)) {
            throw new IllegalArgumentException(String.format("Argument '%s' has incorrect value", argName));
        }
    }
}
