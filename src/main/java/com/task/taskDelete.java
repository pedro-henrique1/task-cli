package com.task;


public class taskDelete {
    ReadJson readJson = new ReadJson();

    public void deleteTask(int id) {
        String jsonString = readJson.ReadToJson();
        jsonString = deleteJsonLineById(jsonString, id);
        readJson.SaveToJson(jsonString);
    }


    private static String deleteJsonLineById(String jsonString, int idToDelete) {
        // Encontrando o objeto com o ID especificado
        String idPattern = "\"id\": " + idToDelete;
        int start = jsonString.indexOf(idPattern);

        if (start == -1) {
            return jsonString;
        }

        int objectStart = jsonString.lastIndexOf("{", start);
        int objectEnd = jsonString.indexOf("}", start) + 1;

//        String objectToRemove = jsonString.substring(objectStart, objectEnd);

        if (jsonString.charAt(objectStart - 1) == ',') {
            objectStart -= 1;  // remove a vírgula anterior
        } else if (jsonString.charAt(objectEnd) == ',') {
            objectEnd += 1;  // remove a vírgula posterior
        }

        jsonString = jsonString.substring(0, objectStart) + jsonString.substring(objectEnd);

        return jsonString;
    }


}
