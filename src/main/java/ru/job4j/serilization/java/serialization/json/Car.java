package ru.job4j.serilization.java.serialization.json;

import java.util.Arrays;

public class Car {

    private final int distance;
    private final String brand;
    private boolean isDizel;
    private Engine engine;
    private String[] pilots;

    public Car(int distance, String brand, boolean isDizel, Engine engine, String[] pilots) {
        this.distance = distance;
        this.brand = brand;
        this.isDizel = isDizel;
        this.engine = engine;
        this.pilots = pilots;
    }

    @Override
    public String toString() {
        return "Car{"
                + "distance=" + distance
                + ", brand='" + brand + '\''
                + ", isDizel=" + isDizel
                + ", engine=" + engine
                + ", pilots=" + Arrays.toString(pilots)
                + '}';
    }
}
