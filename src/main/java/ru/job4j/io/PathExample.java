package ru.job4j.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    //пример1
    public static void main(String[] args) throws IOException {
        Path directory = Paths.get("path/paths");
        Files.createDirectories(directory);
        Path target = Paths.get("path");
        Path pathOne = Path.of("path/paths/path1.txt");
        Files.createFile(pathOne);
        Path pathTwo = Path.of("path/path2.txt");
        Files.createFile(pathTwo);
        //Files.move(path, Path.of("path/path.txt")); переместить
        //Files.delete(directory); удалить директорию
        DirectoryStream<Path> paths = Files.newDirectoryStream(target);
        paths.forEach(System.out::println);
    }

    //пример2
    private static void example() throws IOException {
        Path directory = Paths.get("path/paths");
        Files.createDirectories(directory);
        Path path = Path.of("path/paths/path.txt");
        Files.createFile(path);
        System.out.println("Файл/директория существует?: " + Files.exists(path));
        System.out.println("Это директория?: " + Files.isDirectory(path));
        System.out.println("Это файл?: " + Files.isRegularFile(path));
        System.out.println("Имя файла: " + path.getFileName());
        System.out.println("Путь к файлу абсолютный?: " + path.isAbsolute());
        System.out.println("Родительская директория файла: " + path.getParent());
        System.out.println("Абсолютный путь к файлу: " + path.toAbsolutePath());
        System.out.println("Абсолютный путь к директории: " + directory.toAbsolutePath());
        System.out.println("Доступен для чтения?: " + Files.isReadable(path));
        System.out.println("Доступен для записи?: " + Files.isWritable(path));
    }
}