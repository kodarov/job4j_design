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
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> fileMap = new HashMap<>();

    public void printDuplicates() {
        getDuplicates().forEach((k, v) -> {
            System.out.println(k.getName() + " " + k.getSize() / (1024 * 1024) + " Mb");
            v.forEach(System.out::println);
        });
    }

    private Map<FileProperty, List<Path>> getDuplicates() {
        return fileMap.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        var fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        if (fileMap.containsKey(fileProperty)) {
            fileMap.get(fileProperty).add(file.toAbsolutePath());
        } else {
            ArrayList<Path> paths = new ArrayList<>();
            paths.add(file.toAbsolutePath());
            fileMap.put(fileProperty, paths);
        }
        return super.visitFile(file, attributes);
    }
}