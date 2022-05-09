package ru.job4j.map;

import java.util.Arrays;
import java.util.Objects;

public class Person {
    private boolean gender;
    private int age;
    private long weight;
    private float height;
    private double money;
    private String name;
    private Person[] children;

    public Person(boolean gender,
                  int age,
                  long weight,
                  float height,
                  double money,
                  String name,
                  Person[] children) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.money = money;
        this.name = name;
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return gender == person.gender
                && age == person.age
                && weight == person.weight
                && Float.compare(person.height, height) == 0
                && Double.compare(person.money, money) == 0
                && Objects.equals(name, person.name)
                && Arrays.equals(children, person.children);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += gender ? 1 : 0
                + age
                + (int) (weight ^ weight >>> 32)
                + Float.floatToIntBits(height)
                + (int) (Double.doubleToLongBits(money) ^ Double.doubleToLongBits(money) >>> 32)
                + name.hashCode();
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }
}
