package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            temp = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s: temp) {
            String[] spl = s.split("\\s");
            if (spl[spl.length - 2].equals("404")) {
                rsl.add(s);
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
