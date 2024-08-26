package com.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Command {
    public void add(String scanner) {
        String prefix = "add";
        String textFormat = scanner.substring(prefix.length()).trim();
        try {
            Task task = new Task(textFormat, Task.TaskStatus.TODO);
            task.saveToJsonFile("task.json", task);
            System.out.println("Task added successfully" + task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String scanner) {
        String prefix = "update";
        String descritption = scanner.substring(prefix.length()).replaceAll("\\d+", "").trim();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(scanner.substring(prefix.length()));
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group());
            Task task = new Task();
            task.updateDescription(descritption, id);
        }

    }

    public void delete(String scanner) {
        String prefix = "delete";
        String id = scanner.substring(prefix.length()).trim();

        taskDelete delete = new taskDelete();
        delete.deleteTask(Integer.parseInt(id));

    }

    public void updateTaskProgress(String scanner) {
        String regex = "(\\w+-\\w+)\\s(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(scanner);
        if (matcher.find()) {
            String status = matcher.group(1);  // "in-progress"
            int number = Integer.parseInt(matcher.group(2));  // 1

            ProgressTask task = new ProgressTask();
            task.inProgress(status, String.valueOf(number));
            // Exibir os resultados
        }
    }

    public void updateTaskProgressDone(String scanner) {
        String regex = "(\\w+-\\w+)\\s(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(scanner);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(2));  // 1

            ProgressTask task = new ProgressTask();
            task.inProgressDone(String.valueOf(number));
        }
    }

    public void TaskList() {
        ReadJson readJson = new ReadJson();
        readJson.ReadToJson();
    }
}

