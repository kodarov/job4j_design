package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultipleFile {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/multipleFile.txt")) {
            for (int i = 1; i <= 9; i++) {
                output.write((String.format("1 * %s = %s", i, i)).getBytes());
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
