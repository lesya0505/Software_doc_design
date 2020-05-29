package kafka;

import redis.clients.jedis.Jedis;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Support {
    public BufferedReader getHttpConnection(String url) throws IOException {
        URL data = new URL(url);
        HttpURLConnection con = (HttpURLConnection) data.openConnection();
        return new BufferedReader(new InputStreamReader(con.getInputStream()));
    }

    public String getTimestamp(){
        DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTimeFormatter.format(dateTime);
    }
}