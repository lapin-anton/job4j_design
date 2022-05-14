package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenUnavailableTwoPeriods() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String[] expected = {"10:57:01;10:59:01;", "11:01:02;11:02:02;"};
        List<String> periods = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            while (in.ready()) {
                periods.add(in.readLine());
            }
        }
        assertArrayEquals(expected, periods.toArray());
    }

    @Test
    public void whenUnavailableOnePeriod() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String[] expected = {"10:57:01;11:02:02;"};
        List<String> periods = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            while (in.ready()) {
                periods.add(in.readLine());
            }
        }
        assertArrayEquals(expected, periods.toArray());
    }

}