package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> map = current.stream().collect(Collectors.toMap(User::getId, u -> u));
        for (User u: previous) {
            if (!map.containsKey(u.getId())) {
                deleted++;
            } else if (!map.get(u.getId()).equals(u)) {
                changed++;
            }
        }
        int added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }

}
