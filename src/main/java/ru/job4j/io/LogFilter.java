package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private static final String NOT_FOUND = "404";
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }

    public List<String> filter() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            lines = reader.lines()
                    .filter(line -> {
                        String[] lineArray = line.split(" ");
                        return NOT_FOUND.equals(lineArray[lineArray.length - 2]);
                    })
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            data.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
