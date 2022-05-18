package ru.job4j.serilization.java.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(400, "Volvo", true,
                new Engine(3.5), new String[]{"Petrov", "Ivanov", "Sidorov"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        final String carJson = "{"
                + "\"distance\":300,"
                + "\"brand\":\"BMV\","
                + "\"isDizel\":false,"
                + "\"engine\":"
                + "{\"volume\":4.2},"
                + "\"pilots\":[\"Shumacher\",\"Senna\"]}";
        final Car carBmw = gson.fromJson(carJson, Car.class);
        System.out.println(carBmw);
    }
}
