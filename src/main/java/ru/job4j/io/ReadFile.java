package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
            readText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readText(StringBuilder text) {
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            System.out.println(line);
        }
    }
}