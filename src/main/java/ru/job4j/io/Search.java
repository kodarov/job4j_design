package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Search {

    public static void main(String[] args) throws IOException {
        search(args)
                .forEach(System.out::println);
    }

    public static List<Path> search(String[] args) throws IOException {
        validateArgs(args);
        Path path = Paths.get(args[0]);
        String normalizedExtension = normalizeExtension(args[1]);
        validatePath(path);
        var searcher = new SearchFiles(p -> p.getFileName().toString().toLowerCase().endsWith(normalizedExtension));
        Files.walkFileTree(path, searcher);
        return searcher.getPaths();
    }

    private static void validatePath(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Указанный путь не существует");
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Указанный путь не является директорией");
        }
    }

    private static void validateArgs(String[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            throw new IllegalArgumentException("Не указан путь к директории. Использование: ROOT_FOLDER и расширение");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("Не указано расширение файла");
        }
        if (args.length > 2) {
            throw new IllegalArgumentException("Слишком много аргументов: нужен путь и расширение");
        }
        if (Objects.isNull(args[0]) || args[0].isBlank()) {
            throw new IllegalArgumentException("Путь к директории не может быть пустым");
        }
        if (Objects.isNull(args[1]) || args[1].isBlank()) {
            throw new IllegalArgumentException("Некорректный параметр расширения, укажите расширение файла");
        }
    }

    private static String normalizeExtension(String extension) {
        return extension.startsWith(".") ? extension.toLowerCase() : "." + extension.toLowerCase();
    }
}
