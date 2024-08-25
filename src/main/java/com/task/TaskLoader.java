package com.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskLoader {


    // Carrega o JSON de um arquivo e converte de volta para um objeto Task
    public static Task loadFromJsonFile(String filePath) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        String json = jsonBuilder.toString();

        // Extrai os valores do JSON (simples parsing manual)
        Long id = Long.parseLong(json.substring(json.indexOf("\"id\":") + 5, json.indexOf(",", json.indexOf("\"id\":"))).trim());
        String description = json.substring(json.indexOf("\"description\":") + 15, json.indexOf(",", json.indexOf("\"description\":"))).replace("\"", "").trim();
        String statusString = json.substring(json.indexOf("\"status\":") + 10, json.indexOf(",", json.indexOf("\"status\":"))).replace("\"", "").trim();
        Task.TaskStatus status = Task.TaskStatus.valueOf(statusString);
        String createdAtString = json.substring(json.indexOf("\"createdAt\":") + 13, json.indexOf(",", json.indexOf("\"createdAt\":"))).replace("\"", "").trim();
        String updatedAtString = json.substring(json.indexOf("\"updatedAt\":") + 13, json.indexOf("}", json.indexOf("\"updatedAt\":"))).replace("\"", "").trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime createdAt = LocalDateTime.parse(createdAtString, formatter);
        LocalDateTime updatedAt = LocalDateTime.parse(updatedAtString, formatter);

        return new Task(description, status);
    }




}

