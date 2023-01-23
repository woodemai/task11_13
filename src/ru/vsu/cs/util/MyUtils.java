package ru.vsu.cs.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class MyUtils {

    // чтение из файла
    public static String readLineFromFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        }

    }

    // запись в файл
    public static void writeLineToFile(String filePath, String text) throws IOException {
        List<String> list = List.of(text);
        Path file = Paths.get(filePath);
        Files.write(file, list, StandardCharsets.UTF_8);
    }

}
