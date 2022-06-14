package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findPeak(value, (t, t2) -> comparator.compare(t, t2) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findPeak(value, (t, t2) -> comparator.compare(t, t2) < 0);
    }

    private <T> T findPeak(List<T> list, BiPredicate<T, T> predicate) {
        T result = null;
        for (T item: list) {
            if (result == null || predicate.test(item, result)) {
                result = item;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(0, "user1"));
        list.add(new User(100, "user2"));
        list.add(new User(25, "user3"));
        list.add(new User(99, "user4"));
        System.out.println(new MaxMin().max(list, Comparator.comparingInt(User::getId)));
        System.out.println(new MaxMin().min(list, Comparator.comparingInt(User::getId)));

    }
}
