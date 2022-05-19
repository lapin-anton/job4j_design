package ru.job4j.serilization.java.serialization.json.xml;

public class Profession {
    private String name;

    public Profession(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Profession{"
                + "name='" + name + '\''
                + '}';
    }
}
