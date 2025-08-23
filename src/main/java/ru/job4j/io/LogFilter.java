package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogFilter {
    private static final String NOT_FOUND = "404";
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }

    public List<String> filter() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            lines = reader.lines()
                    .filter(line -> Arrays.asList(line.split(" ")).contains(NOT_FOUND))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
