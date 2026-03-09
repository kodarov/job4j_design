package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс принимает в конструктор 2 параметра - имя файла в который будет записан весь диалог между ботом и пользователем,
 * и имя файла в котором находятся строки с ответами, которые будет использовать бот. Вам нужно реализовать методы:
 * - run(), содержит логику чата;
 * - readPhrases(), читает фразы из файла;
 * - saveLog(), сохраняет лог чата в файл.
 */
public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswersPath;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswersPath = botAnswers;
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat(
                "./data/chatbot/bot.log",
                "./data/chatbot/answers.txt"
        );
        consoleChat.run();
    }

    public void run() {
        var answerList = readPhrases();
        var log = new ArrayList<String>();
        var scanner = new Scanner(System.in);
        String inputText = "";
        boolean isBotEnabled = true;
        System.out.println("Введите текст:");
        try {
            while (!OUT.equals(inputText)) {
                inputText = scanner.nextLine();
                log.add(inputText);
                if (STOP.equals(inputText) || OUT.equals(inputText)) {
                    isBotEnabled = false;
                    continue;
                }
                if (CONTINUE.equals(inputText)) {
                    isBotEnabled = true;
                }
                if (!isBotEnabled) {
                    continue;
                }
                String response = answerList.get(ThreadLocalRandom.current().nextInt(answerList.size()));
                System.out.println(response);
                log.add(response);
            }
        } finally {
            saveLog(log);
        }
    }

    private List<String> readPhrases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswersPath))) {
            List<String> answerList = reader.lines().toList();
            if (answerList.isEmpty()) {
                throw new IllegalStateException("Лист вопросов пустой или отсутствует");
            }
            return answerList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}