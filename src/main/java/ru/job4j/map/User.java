package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User userOne = new User("one", 0, new GregorianCalendar(2000, 1, 1));
        User userTwo = new User("one", 0, new GregorianCalendar(2000, 1, 1));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        for (User key: map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }
}
