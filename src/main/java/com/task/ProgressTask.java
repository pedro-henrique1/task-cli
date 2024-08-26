package com.task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgressTask {
    ReadJson readJson = new ReadJson();

    public void inProgress(String id) {
        String status = String.valueOf(Task.TaskStatus.IN_PROGRESS);
        String jsonString = readJson.ReadToJson();
        jsonString = updateByField(id, jsonString, status, "status");
        readJson.SaveToJson(jsonString);


    }

    public void inProgressDone(String id) {
        String status = String.valueOf(Task.TaskStatus.DONE);
        String jsonString = readJson.ReadToJson();
        jsonString = updateByField(id, jsonString, status, "status");
        readJson.SaveToJson(jsonString);
    }


    public String updateByField(String id, String jsonString, String field, String fieldJson) {
        String idPattern = "\"id\": " + id;
        int start = jsonString.indexOf(idPattern);

        if (start == -1) {
            return jsonString;
        }
        jsonString = jsonString.replaceFirst("(?s)(\"id\":\\s*" + id + "[^}]*?\"" + fieldJson + "\":\\s*\")[^\"]*(\")", "$1" + field + "$2");
        String updatedAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        jsonString = jsonString.replaceFirst("(?s)(\"id\": " + id + "[^}]*?\"updatedAt\":\\s*\")[^\"]*(\")", "$1" + updatedAt + "$2");
        return jsonString;
    }


}
