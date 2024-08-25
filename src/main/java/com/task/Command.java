package com.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Command {
    public void add(String scanner) {
        String prefix = "add";
        String textFormat = scanner.substring(prefix.length()).trim();
        try {
            Task task = new Task(textFormat, Task.TaskStatus.IN_PROGRESS);
            task.saveToJsonFile("task.json", task);
            System.out.println("Tarefa salva em task.json" + task);
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


}

