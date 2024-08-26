package com.task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgressTask {


    public void inProgress(String type, String id) {
        String filePath = "task.json";
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String jsonString = jsonContent.toString();

        jsonString = UpdateJsonLineById(jsonString, id);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String UpdateJsonLineById(String jsonString, String id) {
        String idPattern = "\"id\": " + id;
        int start = jsonString.indexOf(idPattern);

        if (start == -1) {
            return jsonString;
        }

        jsonString = jsonString.replaceFirst("(?s)(\"id\": " + id + "[^}]*?\"status\":\\s*\")[^\"]*(\")", "$1" + Task.TaskStatus.IN_PROGRESS + "$2");

        String updatedAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        jsonString = jsonString.replaceFirst("(?s)(\"id\": " + id + "[^}]*?\"updatedAt\":\\s*\")[^\"]*(\")", "$1" + updatedAt + "$2");
        return jsonString;

    }


    public void inProgressDone( String id) {
        String filePath = "task.json";
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String jsonString = jsonContent.toString();

        jsonString = updateDoneById(jsonString, id);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String updateDoneById(String jsonString, String id) {
        String idPattern = "\"id\": " + id;
        int start = jsonString.indexOf(idPattern);

        if (start == -1) {
            return jsonString;
        }

        jsonString = jsonString.replaceFirst("(?s)(\"id\": " + id + "[^}]*?\"status\":\\s*\")[^\"]*(\")", "$1" + Task.TaskStatus.DONE + "$2");

        String updatedAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        jsonString = jsonString.replaceFirst("(?s)(\"id\": " + id + "[^}]*?\"updatedAt\":\\s*\")[^\"]*(\")", "$1" + updatedAt + "$2");
        return jsonString;
    }


}
