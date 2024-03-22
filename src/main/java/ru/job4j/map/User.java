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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (children != user.children) {
            return false;
        }
        if (!Objects.equals(name, user.name)) {
            return false;
        }
        return Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Map<User, Object> users = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User lexUser = new User("Lex", 3, birthday);
        int hashcodeLex = lexUser.hashCode();
        int hashLex = hashcodeLex ^ (hashcodeLex >> 16);
        int bucketLex = hashLex & 15;
        users.put(lexUser, new Object());

        User alexUser = new User("Lex", 3, birthday);
        int hashcodeAlex = alexUser.hashCode();
        int hashAlex = hashcodeAlex ^ (hashcodeAlex >> 16);
        int bucketAlex = hashAlex & 15;
        users.put(alexUser, new Object());

        System.out.printf("Lex hashCode %d, hash %d, bucket %d" + System.lineSeparator(), hashcodeLex, hashLex, bucketLex);
        System.out.printf("Alex hashCode %d, hash %d, bucket %d" + System.lineSeparator(), hashcodeAlex, hashAlex, bucketAlex);
        System.out.println(users);
    }
}
