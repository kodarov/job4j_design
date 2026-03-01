package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> fileMap = new HashMap<>();

    public void printDuplicates() {
        fileMap.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e -> {
                    System.out.printf("%s - %d B (%.2f Mb)%n",
                            e.getKey().getName(),
                            e.getKey().getSize(),
                            e.getKey().getSize() / (1024 * 1024.0));
                    e.getValue().forEach(System.out::println);
                });
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        var fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        fileMap.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }
}