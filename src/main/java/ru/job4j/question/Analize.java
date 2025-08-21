package ru.job4j.question;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> currentMap = current.stream()
                .collect(Collectors.toMap(User::getId, user -> user));

        for (User user : previous) {
            User currentUser = currentMap.get(user.getId());
            if (Objects.isNull(currentUser)) {
                deleted++;
            } else if (user.getId() == currentUser.getId() && !currentUser.getName().equals(user.getName())) {
                changed++;
                currentMap.remove(user.getId());
            }
        }
        int added = current.size() - (previous.size() - deleted);
        return new Info(added, changed, deleted);
    }

}