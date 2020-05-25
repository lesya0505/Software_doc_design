package tickets;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderGenerator {

    Random random = new Random();
    static final File USERNAMES = new File("src/main/Usernames.csv");
    static final File CONCERT_NAMES = new File("src/main/ConcertNames.csv");
    List<String> username;
    List<String> concertName;

    {
        try {
            username = Files.readAllLines(USERNAMES.toPath(), Charset.defaultCharset());
            concertName = Files.readAllLines(CONCERT_NAMES.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateUsername() {
        return username.get(random.nextInt(username.size()));
    }

    public String generateConcertName() {
        return concertName.get(random.nextInt(concertName.size()));
    }

    public String generateDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(new Date());
    }

    public String generateConcertText() {
        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(30);
        for (int i = 0; i < 30; i++) {
            int index
                    = (int) (randomString.length()
                    * Math.random());
            sb.append(randomString
                    .charAt(index));
        }
        return sb.toString();
    }

    public int generateSeat() {
        return random.nextInt(100);
    }

    public String generatePrice() {
        String[] pricesArray = {"200","250","300", "350", "400", "450","500", "550", "600", "650", "700"};
        int randomPrice = random.nextInt(pricesArray.length);
        return pricesArray[randomPrice];
    }

    public void generateOrder() {
        try {
            PrintWriter printWriter = new PrintWriter("Order.csv", "UTF-8");
            for (int i = 0; i <= 1000; i++) {
                printWriter.println(generateUsername()+ " " + generateConcertName() + " " +
                        generateConcertText() + " " + generatePrice() + " " +
                        generateDate() + " " + generateSeat());
            }
            printWriter.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}