package ru.job4j.io;

import java.io.*;

public class Analysis {

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }

    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] lineArray = line.split(" ", 2);
                if (lineArray.length != 2) {
                    continue;
                }
                if (sb.isEmpty() && ("400".equals(lineArray[0])) || "500".equals(lineArray[0])) {
                    sb.append(lineArray[1]).append(";");
                }
                if (!sb.isEmpty() && ("200".equals(lineArray[0]) || "300".equals(lineArray[0]))) {
                    sb.append(lineArray[1]).append(";");
                    output.print(sb);
                    output.println();
                    sb.delete(0, sb.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}