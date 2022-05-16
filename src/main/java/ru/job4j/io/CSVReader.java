package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validateParam(argsName);
        String data;
        StringBuilder rsl;
        boolean isFirstRow = true;
        int column;
        List<String> filteredColumns = Arrays.stream(argsName.get("filter").split(",")).toList();
        List<Integer> filteredColumnInds = new ArrayList<>();
        OutputStream out = "stdout".equals(argsName.get("out"))
                ? System.out
                : new FileOutputStream(argsName.get("out"));
        try (BufferedReader reader = new BufferedReader(new FileReader(argsName.get("path"), StandardCharsets.UTF_8));
             PrintWriter writer = new PrintWriter(out)) {
            while (reader.ready()) {
                data = reader.readLine();
                Scanner scanner = new Scanner(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)))
                        .useDelimiter(argsName.get("delimiter"));
                rsl = new StringBuilder();
                column = 0;
                if (isFirstRow) {
                    while (scanner.hasNext()) {
                        String value = scanner.next();
                        if (filteredColumns.contains(value)) {
                            filteredColumnInds.add(column);
                            rsl.append(value);
                            rsl.append(argsName.get("delimiter"));
                        }
                        column++;
                    }
                    isFirstRow = false;
                } else {
                    while (scanner.hasNext()) {
                        String value = scanner.next();
                        if (filteredColumnInds.contains(column)) {
                            rsl.append(value);
                            rsl.append(argsName.get("delimiter"));
                        }
                        column++;
                    }
                }
                rsl.deleteCharAt(rsl.length() - 1);
                writer.println(rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
    }

    private static void validateParam(ArgsName argsName) {
        String pathFormat = "([a-zA-Z]:)?([\\\\\\/]?[a-zA-Z0-9_.-]+)+[\\\\\\/]?";
        String delimFormat = "[,;]";
        String filterFormat = "(\\w+)(,\\w+)*";
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        if (!Pattern.matches(pathFormat, path)) {
            throw new IllegalArgumentException(String.format("Param '%s' has incorrect path format", path));
        }
        if (!Pattern.matches(delimFormat, delimiter)) {
            throw new IllegalArgumentException(String.format("Param '%s' has incorrect delimiter format", delimiter));
        }
        if (!Pattern.matches(pathFormat, out) && !"stdout".equals(out)) {
            throw new IllegalArgumentException(String.format("Param '%s' has incorrect output format", out));
        }
        if (!Pattern.matches(filterFormat, filter)) {
            throw new IllegalArgumentException(String.format("Param '%s' has incorrect filter format", filter));
        }
    }
}
