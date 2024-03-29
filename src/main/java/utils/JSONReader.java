package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

class JSONReader {

    JSONObject readJson(String pathToJson) {
        JSONParser parser = new JSONParser();
        File file = new File("src/main/resources");
        String absolutePath = file.getAbsolutePath() + pathToJson;
        try {

            Object obj = parser.parse(new FileReader(absolutePath));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
