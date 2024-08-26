package com.task;

import java.util.ArrayList;

public class ListTask {

    public void listDone(Task.TaskStatus statusToFilter) {
        ReadJson readJson = new ReadJson();
        String json = readJson.ReadToJson();
        ArrayList<String> objects = extractJsonObjects(json);
        for (String jsonObject : objects) {
            if (jsonObject.contains("\"status\": \"" + statusToFilter + "\"")) {
                System.out.println(jsonObject);
            }
        }
    }


    private static ArrayList<String> extractJsonObjects(String jsonString) {
        ArrayList<String> objects = new ArrayList<>();
        jsonString = jsonString.trim();
        if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
            jsonString = jsonString.substring(1, jsonString.length() - 1);  // Remove brackets
            int startIndex = 0;
            int bracketCount = 0;
            boolean inQuotes = false;

            for (int i = 0; i < jsonString.length(); i++) {
                char ch = jsonString.charAt(i);

                if (ch == '"') {
                    inQuotes = !inQuotes;
                }

                if (!inQuotes) {
                    if (ch == '{') bracketCount++;
                    if (ch == '}') bracketCount--;
                    if (ch == ',' && bracketCount == 0) {
                        objects.add(jsonString.substring(startIndex, i).trim());
                        startIndex = i + 1;
                    }
                }
            }
            if (startIndex < jsonString.length()) {
                objects.add(jsonString.substring(startIndex).trim());
            }
        }
        return objects;
    }

}
