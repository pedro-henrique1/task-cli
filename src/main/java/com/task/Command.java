package com.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Command {
    public void add(String scanner) {
        String prefix = "add";
        String textFormat = scanner.substring(prefix.length()).trim();
        if (textFormat.isEmpty()) {
            System.out.println("Digite a descrição da tarefa");
        } else {
            try {
                Task task = new Task(textFormat, Task.TaskStatus.TODO);
                task.saveToJsonFile("task.json", task);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
            task.updateDescription(descritption, String.valueOf(id));
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
            int number = Integer.parseInt(matcher.group(2));  // 1

            ProgressTask task = new ProgressTask();
            task.inProgress(String.valueOf(number));
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
        System.out.println(readJson.ReadToJson());
    }
}

