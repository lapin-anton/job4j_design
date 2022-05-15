package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException(String.format("Param with key '%s' is not exist", key));
        }
        return rsl;
    }

    private void parse(String[] args) {
        String argFormat = "^-\\w+=.+$";
        for (String arg: args) {
            if (!Pattern.matches(argFormat, arg)) {
                throw new IllegalArgumentException(String.format("Argument '%s' has incorrect format.", arg));
            }
            int delimIndex = arg.indexOf("=");
            String key = arg.substring(1, delimIndex);
            String value = arg.substring(delimIndex + 1);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
