package kafka;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleOutput implements DisplayStrategy {
    private static final String URL = "https://data.cityofnewyork.us/resource/erm2-nwe9.json";
    Support util = new Support();

    public void display() {
        try {
            System.out.println("Loading data ...");
            JSONArray jsonArray;
            BufferedReader br = util.getHttpConnection(URL);
            String inputLine;
            StringBuilder response = new StringBuilder();
            int count = 1;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
                if (count % 100 == 0) {
                    jsonArray = new JSONArray(response.toString() + ']');
                    writeDataToConsole(jsonArray);
                }
                count += 1;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToConsole(JSONArray jsonArray) {
        int limit = 1000;
        int start = 1;
        int end = 0;
        int batch = 100;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            System.out.println("Record: " + i + " " + jsonObject);
            if (i == batch - 1) {
                end = i;
                System.out.println("Records " + start + " : " + end);
                System.out.println("Loading ...");
                batch = batch + 100;
            }
            if (i == 999) {
                System.out.println("Records: " + limit);
                System.out.println("Completed.");
            }
            start = end + 1;
        }
    }
}