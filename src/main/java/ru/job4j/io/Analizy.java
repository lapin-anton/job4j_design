package ru.job4j.io;

import java.io.*;
import java.util.regex.Pattern;

public class Analizy {
    public void unavailable(String source, String target) {
        String regex = "^[\\d]{3}\\s([\\d]{2}:){2}[\\d]{2}$";
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            int lastCode = 0;
            String begin = null;
            while (reader.ready()) {
                String s = reader.readLine();
                if (!"".equals(s) && Pattern.matches(regex, s)) {
                    String[] items = s.split("\\s");
                    int code = Integer.parseInt(items[0]);
                    String time = items[1];
                    if (code >= 400 && code <= 500 && lastCode >= 200 && lastCode <= 300) {
                        begin = time;
                    } else if (code >= 200 && code <= 300 && lastCode >= 400 && lastCode <= 500) {
                        writer.printf("%s;%s;%s", begin, time, System.lineSeparator());
                    }
                    lastCode = code;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.txt", "unavailable.csv");
    }

}
