package com.task;

import java.io.*;

public class taskDelete {

    public void deleteTask(int id) {
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

        jsonString = deleteJsonLineById(jsonString, id);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String deleteJsonLineById(String jsonString, int idToDelete) {
        // Encontrando o objeto com o ID especificado
        String idPattern = "\"id\": " + idToDelete;
        int start = jsonString.indexOf(idPattern);

        System.out.println(start);
        if (start == -1) {
            return jsonString;
        }

        int objectStart = jsonString.lastIndexOf("{", start);
        int objectEnd = jsonString.indexOf("}", start) + 1;

        String objectToRemove = jsonString.substring(objectStart, objectEnd);

        if (jsonString.charAt(objectStart - 1) == ',') {
            objectStart -= 1;  // remove a vírgula anterior
        } else if (jsonString.charAt(objectEnd) == ',') {
            objectEnd += 1;  // remove a vírgula posterior
        }

        jsonString = jsonString.substring(0, objectStart) + jsonString.substring(objectEnd);

        return jsonString;
    }




}
