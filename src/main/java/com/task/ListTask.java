package com.task;

public class ListTask {

    public void listDone(Task.TaskStatus statusToFilter) {
        ReadJson readJson = new ReadJson();
        String json = readJson.ReadToJson();
        json = json.substring(1, json.length() - 1);
        String[] jsonObjects = json.split("},\\s*\\{");
        for (String jsonObject : jsonObjects) {
            if (jsonObject.contains("\"status\": \"" + statusToFilter + "\"")) {
                System.out.println(jsonObject);
            }
        }
    }


}
