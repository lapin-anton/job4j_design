package ru.job4j.serilization.java.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(500, "Ferrari", false, new Engine(2.5), "Shumacher", "Senna");
        JSONObject jsonCar = new JSONObject();
        jsonCar.put("distance", car.getDistance());
        jsonCar.put("brand", car.getBrand());
        jsonCar.put("isDizel", car.isDizel());
        jsonCar.put("engine", new JSONObject(car.getEngine()));
        jsonCar.put("pilots", new JSONArray(car.getPilots()));
        System.out.println(jsonCar);
    }
}
