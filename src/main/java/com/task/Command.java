package com.task;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Command {
    DatabaseOperations dbOps = new DatabaseOperations();
    private Timestamp creationDateTime;
    private Timestamp updateDateTime;


    public void add(String scanner) {
        String textFormat = scanner.replaceAll("\\b\\w+\\b(?<![\\\"])(?![\\\"])", "");
        dbOps.insertData(textFormat, DatabaseOperations.TaskStatus.IN_PROGRESS, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

    }

    public void update(String scanner) {
        String id = scanner.replaceAll("^update (\\d+).*", "$1");
        String textFormat = scanner.replaceAll("\\b\\w+\\b(?<![\\\"])(?![\\\"])", "");

        dbOps.updateData(Integer.parseInt(id), textFormat, Timestamp.valueOf(LocalDateTime.now()));

    }
}
