package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String userPhrase = null;
            String botPhrase = null;
            boolean isActive = true;
            while (!OUT.equals(userPhrase)) {
                userPhrase = reader.readLine();
                log.add(String.format("You: %s", userPhrase));
                if (STOP.equals(userPhrase) || OUT.equals(userPhrase)) {
                    isActive = false;
                }
                if (CONTINUE.equals(userPhrase)) {
                    isActive = true;
                }
                if (isActive) {
                    botPhrase = botPhrases.get((int) (Math.random() * (botPhrases.size() - 1)));
                    System.out.printf("bot: %s%n", botPhrase);
                    log.add(String.format("bot: %s", botPhrase));
                }
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            while (in.ready()) {
                rsl.add(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\data\\console_chat\\log.txt", ".\\data\\console_chat\\answers.txt");
        cc.run();
    }
}
