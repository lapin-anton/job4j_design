package ru.job4j.serilization.java.serialization.json.xml;

import java.util.Arrays;

public class Employee {
    private String name;
    private boolean isLead;
    private int salary;
    private Profession profession;
    private String[] skills;

    public Employee(String name, boolean isLead, int salary, Profession profession, String[] skills) {
        this.name = name;
        this.isLead = isLead;
        this.salary = salary;
        this.profession = profession;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", isLead=" + isLead
                + ", salary=" + salary
                + ", profession=" + profession
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }
}
