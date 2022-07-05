package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    @Ignore
    public void whenTemplateIsNotNullAndMapHasAllArgs() {
        String template = "Wake up, ${username}! ${subject} has you!";
        Map<String, String> args = new HashMap<>();
        args.put("username", "Neo");
        args.put("subject", "Matrix");
        Generator generator = new GeneratorImpl();
        String rsl = generator.produce(template, args);
        String expected = "Wake up, Neo! Matrix has you!";
        assertEquals(expected, rsl);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenTemplateIsNullAndMapHasAllArgs() {
        String template = null;
        Map<String, String> args = new HashMap<>();
        args.put("username", "Neo");
        args.put("subject", "Matrix");
        Generator generator = new GeneratorImpl();
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenTemplateIsNotNullAndMapIsNull() {
        String template = "Wake up, ${username}! ${subject} has you!";
        Map<String, String> args = null;
        Generator generator = new GeneratorImpl();
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenTemplateIsNotNullAndMapHasArgsLessThanNecessary() {
        String template = "Wake up, ${username}! ${subject} has you!";
        Map<String, String> args = new HashMap<>();
        args.put("username", "Neo");
        Generator generator = new GeneratorImpl();
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenTemplateIsNotNullAndMapHasArgsMoreThanNecessary() {
        String template = "Wake up, ${username}!";
        Map<String, String> args = new HashMap<>();
        args.put("username", "Neo");
        args.put("subject", "Matrix");
        Generator generator = new GeneratorImpl();
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenTemplateIsNotNullAndMapIsEmpty() {
        String template = "Wake up, ${username}!";
        Map<String, String> args = new HashMap<>();
        Generator generator = new GeneratorImpl();
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenTemplateIsNotNullButHasNotKeys() {
        String template = "Wake up!";
        Map<String, String> args = new HashMap<>();
        args.put("username", "Neo");
        args.put("subject", "Matrix");
        Generator generator = new GeneratorImpl();
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenTemplateIsNotNullAndMapHasNotNecessaryArgs() {
        String template = "Wake up ${username}! ${subject} has you!";
        Map<String, String> args = new HashMap<>();
        args.put("dog", "Neo");
        args.put("cat", "Matrix");
        Generator generator = new GeneratorImpl();
        generator.produce(template, args);
    }

}