package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            while (reader.ready()) {
                String param = reader.readLine().trim();
                if (!"".equals(param) && !param.startsWith("#") && param.contains("=")) {
                    int delimPos = param.indexOf("=");
                    if (delimPos > 0 && delimPos < param.length() - 1) {
                        String name = param.substring(0, delimPos);
                        String value = param.substring(delimPos + 1);
                        values.put(name, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config cfg = new Config("app.properties");
        System.out.println(cfg);
        cfg.load();
    }
}
