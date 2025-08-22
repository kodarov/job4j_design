package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            while (input.available() > 0) {
                text.append((char) input.read());
            }
            String[] numbers = text.toString().split(System.lineSeparator());
            readEvenNumber(numbers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readEvenNumber(String[] numbers) {
        for (String number : numbers) {
            try {
                int num = Integer.parseInt(number);
                if (num % 2 == 0) {
                    System.out.println(num);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}