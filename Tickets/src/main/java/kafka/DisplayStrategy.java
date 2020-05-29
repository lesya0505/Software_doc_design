package kafka;

import com.microsoft.azure.eventhubs.EventHubException;

import java.io.IOException;

public interface DisplayStrategy {
    void display() throws IOException, EventHubException;
}