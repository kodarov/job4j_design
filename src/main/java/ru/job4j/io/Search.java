package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, ".js").forEach(System.out::println);
    }

    public static List<Path> search(Path root, String extend) throws IOException {
        Predicate<Path> condition = path -> path.toFile().getName().endsWith(extend);
        validate(root, extend);
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(Path path, String extension) {
        if (Objects.isNull(path) || path.toFile().getName().isBlank()) {
            throw new IllegalArgumentException("Root path is empty");
        }
        if (Objects.isNull(extension) || extension.isBlank() || !extension.startsWith(".")) {
            throw new IllegalArgumentException("Incorrect expansion argument, "
                                               + "indicate the actual expansion of the format \".txt\"");
        }
    }

}