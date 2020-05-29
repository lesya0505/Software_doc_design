package kafka;

import com.microsoft.azure.eventhubs.EventHubException;

import java.io.IOException;
import java.util.Scanner;

public class KafkaMain {
    public static void main(String[] args) throws IOException, EventHubException {
        Context context;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Press 1 to load data to Console");
        System.out.println("Press 2 to load data to  Event Hub");

        int algorithm = scanner.nextInt();

        switch (algorithm) {
            case 1:
                context = new Context(new ConsoleOutput());
                context.executeStrategy();
                break;
            case 2:
                context = new Context(new EventHub());
                context.executeStrategy();
                break;
        }
    }
}
