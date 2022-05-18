package ru.job4j.serilization.java.serialization.json;

public class Engine {
    private final double volume;

    public Engine(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "volume=" + volume
                + '}';
    }
}
