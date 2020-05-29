package kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EventHub implements DisplayStrategy {
    private static final String URL = "https://data.cityofnewyork.us/resource/erm2-nwe9.json";
    private static final String NAMESPACE_NAME = "labsoft";
    private static final String EVENT_HUB_NAME = "labsoft";
    private static final String SAS_KEY_NAME = "Endpoint=sb://labsoft.servicebus.windows.net/;SharedAccessKeyName=labsoft;SharedAccessKey=H7hRg4rajs7vtyketUngXWB5jQNSo5uBuLUAyjtkkbk=;EntityPath=labsoft";
    private static final String SAS_KEY = "H7hRg4rajs7vtyketUngXWB5jQNSo5uBuLUAyjtkkbk=";
    Support util = new Support();

    public void display() throws IOException, EventHubException {
        final ConnectionStringBuilder eventHubConnectionString = new ConnectionStringBuilder()
                .setNamespaceName(NAMESPACE_NAME)
                .setEventHubName(EVENT_HUB_NAME)
                .setSasKeyName(SAS_KEY_NAME)
                .setSasKey(SAS_KEY);

        final Gson gson = new GsonBuilder().create();
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        final EventHubClient eventHubClient = EventHubClient.createSync(eventHubConnectionString.toString(), executorService);

        try {
            JSONArray jsonArray;
            System.out.println("Sending data to Event Hub ...");
            BufferedReader br = util.getHttpConnection(URL);
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            jsonArray = new JSONArray(response.toString());
            writeDataToConsole(jsonArray, gson, eventHubClient);
            System.out.println(util.getTimestamp() + ": Completed");
        } finally {
            eventHubClient.closeSync();
            executorService.shutdown();
        }
    }

    public void writeDataToConsole(JSONArray jsonArray, Gson gson, EventHubClient eventHubClient) throws EventHubException {
        int limit = 1000;
        int start = 1;
        int end = 0;
        int batch = 100;
        for (int i = 0; i < jsonArray.length(); i++) {
            writeDataToEvent(jsonArray, gson, eventHubClient, i);
            if (i == batch - 1) {
                end = i;
                System.out.println("Records " + start + " : " + end);
                System.out.println("Sending ...");
                batch = batch + 100;
            }
            if (i == 999) {
                System.out.println("Records: " + limit);
                System.out.println("Completed.");
            }
            start = end + 1;
        }
    }

    public void writeDataToEvent(JSONArray jsonArray, Gson gson, EventHubClient eventHubClient, int i) throws EventHubException {
        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
        String payload = "Message: " + jsonObject;
        System.out.println(i + " " + payload);
        byte[] payloadBytes = gson.toJson(payload).getBytes(Charset.defaultCharset());
        EventData sendEvent = EventData.create(payloadBytes);
        eventHubClient.sendSync(sendEvent);
    }
}