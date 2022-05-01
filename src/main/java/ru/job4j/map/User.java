package ru.job4j.map;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar date = GregorianCalendar.from(
                ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()));
        User userOne = new User("one", 0, date);
        User userTwo = new User("one", 0, date);
        System.out.println(userOne.hashCode());
        System.out.println(userTwo.hashCode());
        System.out.println(userOne.equals(userTwo));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        for (User key: map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }
}
