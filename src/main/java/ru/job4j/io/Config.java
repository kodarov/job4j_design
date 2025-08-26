package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

    public void load() {
        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            input.lines().forEach(e -> {
                if (e.isBlank() || e.startsWith("#")) {
                    return;
                }
                String[] line = e.split("=", 2);
                checkIncorrectLine(line);
                values.put(line[0], line[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return value;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    private void checkIncorrectLine(String[] lineArray) {
        if (Objects.isNull(lineArray[0])
            || lineArray[0].isBlank()
            || lineArray[1].isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}