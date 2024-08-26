package com.task;

import java.io.*;

public class ReadJson {
    public String ReadToJson() {

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("task.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String jsonString = jsonContent.toString();
        System.out.println(jsonString);
        return jsonString;
    }
}


