package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        validateArgs(args);
        ArgsName argsName = ArgsName.of(args);
        Path root = Path.of(argsName.get("d"));
        List<Path> sources = Search.searchExclude(argsName.get("d"), argsName.get("e"));
        zip.packFiles(sources, root, new File(argsName.get("o")));
    }

    private static void validateArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        String zipName = argsName.get("o");
        if (zipName == null || zipName.isBlank()) {
            throw new IllegalArgumentException("Output file is empty");
        }
        Path outputPath = Path.of(zipName);
        if (Files.exists(outputPath) && Files.isDirectory(outputPath)) {
            throw new IllegalArgumentException("Output path is a directory");
        }
        if (Files.exists(outputPath)) {
            throw new IllegalArgumentException("Output path file exist");
        }
        Path parent = outputPath.toAbsolutePath().getParent();
        if (parent != null && !Files.exists(parent)) {
            throw new IllegalArgumentException("Output directory does not exist");
        }
    }

    public void packFiles(List<Path> sources, Path root, File target) {
        try (ZipOutputStream zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zipOutput.putNextEntry(new ZipEntry(root.relativize(path).toString()));
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zipOutput.write(input.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)));
             BufferedInputStream input = new BufferedInputStream(new FileInputStream(source))
        ) {
            zipOutput.putNextEntry(new ZipEntry(source.getName()));
            zipOutput.write(input.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
