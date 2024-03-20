package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

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
        Map<User, Object> users = new HashMap<>(16);
        User lexUser = new User("Lex", 3, Calendar.getInstance());
        int hashcodeLex = lexUser.hashCode();
        int hashLex = hashcodeLex ^ (hashcodeLex >> 16);
        int bucketLex = hashLex & 15;
        users.put(lexUser, new Object());

        User alexUser = new User("Lex", 3, Calendar.getInstance());
        int hashcodeAlex = alexUser.hashCode();
        int hashAlex = hashcodeAlex ^ (hashcodeAlex >> 16);
        int bucketAlex = hashAlex & 15;
        users.put(alexUser, new Object());
        System.out.printf("hashCode1 %d, hash %d, bucket %d" + System.lineSeparator(), hashcodeLex, hashLex, bucketLex);
        System.out.printf("hashCode1 %d, hash %d, bucket %d" + System.lineSeparator(), hashcodeAlex, hashAlex, bucketAlex);
        System.out.println(users);
    }
}
