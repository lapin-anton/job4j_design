package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<Status> statuses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            while (reader.ready()) {
                String s = reader.readLine();
                if (!"".equals(s)) {
                    String[] items = s.split("\\s");
                    int code = Integer.parseInt(items[0]);
                    String time = items[1];
                    statuses.add(new Status(code, time));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            int lastCode = 0;
            String begin = null;
            for (Status s: statuses) {
                if (s.getCode() >= 400 && s.getCode() <= 500 && lastCode >= 200 && lastCode <= 300) {
                    begin = s.getTime();
                } else if (s.getCode() >= 200 && s.getCode() <= 300 && lastCode >= 400 && lastCode <= 500) {
                    writer.printf("%s;%s;%s", begin, s.getTime(), System.lineSeparator());
                }
                lastCode = s.getCode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.txt", "unavailable.csv");
    }

    private static class Status {
        private int code;
        private String time;

        public Status(int code, String time) {
            this.code = code;
            this.time = time;
        }

        public int getCode() {
            return code;
        }

        public String getTime() {
            return time;
        }
    }
}
