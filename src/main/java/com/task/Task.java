package com.task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Task {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);
    private Long id;
    private String description;
    private TaskStatus status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    ReadJson readJson = new ReadJson();
    ProgressTask progressTask = new ProgressTask();

    public Task(String description, TaskStatus status) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.description = description;
        this.status = status;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();

    }

    public Task() {

    }

    public Long getId() {
        return id;
    }

    public String toJson() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return String.format("{\"id\": %d, \"description\": \"%s\", \"status\": \"%s\", \"createdAt\": \"%s\", \"updatedAt\": \"%s\"}", id, description, status, created_at.format(formatter), updated_at.format(formatter));
    }

    public void saveToJsonFile(String filePath, Task task) {
        List<String> tasksJson = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line);
                }
                if (jsonContent.length() > 2 && jsonContent.charAt(0) == '[' && jsonContent.charAt(jsonContent.length() - 1) == ']') {
                    jsonContent.setLength(jsonContent.length() - 1);
                    jsonContent.deleteCharAt(0);
                    tasksJson.add(jsonContent.toString());
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo existente: " + e.getMessage());
            }
        }

        tasksJson.add(task.toJson());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("[\n");
            for (int i = 0; i < tasksJson.size(); i++) {
                writer.write(tasksJson.get(i));
                if (i < tasksJson.size() - 1) {
                    writer.write(",\n");
                }
            }
            writer.write("\n]");
            System.out.println("Task added successfully (ID:" + getId() + ")");
        } catch (IOException e) {
            System.err.println("Erro ao salvar a tarefa no arquivo: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return toJson();
    }

    public enum TaskStatus {
        TODO, IN_PROGRESS, DONE
    }

    public void updateDescription(String newDescription, String id) {
        String jsonString = readJson.ReadToJson();
        jsonString = progressTask.updateByField(id, jsonString, newDescription, "description");
        readJson.SaveToJson(jsonString);
    }


}
