package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    @Test
    public void whenFindMinId() {
        MaxMin maxMin = new MaxMin();
        List<User> users = new ArrayList<>();
        users.add(new User(0, "user1"));
        users.add(new User(150, "user2"));
        users.add(new User(15, "user3"));
        users.add(new User(149, "user4"));
        assertThat(maxMin.min(users, Comparator.comparingInt(User::getId)), is(new User(0, "user1")));
    }

    @Test
    public void whenFindMaxId() {
        MaxMin maxMin = new MaxMin();
        List<User> users = new ArrayList<>();
        users.add(new User(0, "user1"));
        users.add(new User(150, "user2"));
        users.add(new User(15, "user3"));
        users.add(new User(149, "user4"));
        assertThat(maxMin.max(users, Comparator.comparingInt(User::getId)), is(new User(150, "user2")));
    }
}