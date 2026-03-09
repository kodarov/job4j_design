package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1. Создать паттерн: Pattern pattern = Pattern.compile("Я учусь на Job4j");
 * 2. Создать сопоставление:
 * String text = "Я учусь на Job4j";
 * Matcher matcher = pattern.matcher(text);
 * 3. Сравнить matcher.matches();
 */
public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        String textOne = "Я учусь на Job4j";
        Matcher matcherOne = pattern.matcher(textOne);
        boolean isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        String textTwo = "Я учусь на курсе Job4j";
        Matcher matcherTwo = pattern.matcher(textTwo);
        boolean isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);
    }

    public static void exampleOne() {
        Pattern pattern = Pattern.compile("Job4j");

        String textOne = "Job4j";
        Matcher matcherOne = pattern.matcher(textOne);
        boolean isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        String textTwo = "job4j";
        Matcher matcherTwo = pattern.matcher(textTwo);
        boolean isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);
    }

    public static void exampleTwo() {
        Pattern pattern = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);

        String textOne = "Job4j";
        Matcher matcherOne = pattern.matcher(textOne);
        boolean isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        String textTwo = "joB4J";
        Matcher matcherTwo = pattern.matcher(textTwo);
        boolean isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);
    }

    public static void exampleThree() {
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Я учусь на курсе Job4j";
        Matcher matcher = pattern.matcher(text);
        boolean isPresent = matcher.find();
        System.out.println(isPresent);
    }

    public static void exampleFour() {
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Job4j и Job4j и Job4j";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение");
        }
    }

    public static void exampleFive() {
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }

    public static void exampleSix(String[] args) {
        Pattern pattern = Pattern.compile("11");
        String text = "111111";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }

    /**
     * String.split()
     *
     * @param args
     */
    public static void exampleSeven(String[] args) {
        String string = "123+=-456:/789";
        String[] result = string.split("\\D+");
        System.out.println(Arrays.toString(result));
    }

    /**
     * найти "дд.мм.гггг"
     *
     * @param args
     */
    public static void exampleEight(String[] args) {
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        String text = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }

    /**
     * найти "дд.мм.гггг" и ограничением слова по \b
     *
     * @param args
     */
    public static void exampleNine(String[] args) {
        Pattern pattern = Pattern.compile("\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b");
        String text = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }

    /**
     * поиск эл.почты
     *
     * @param args
     */
    public static void exampleTen(String[] args) {
        Pattern pattern = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }
}