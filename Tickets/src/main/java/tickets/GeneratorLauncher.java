package tickets;

import tickets.OrderGenerator;

public class GeneratorLauncher {
    public static void main(String[] args) {
        OrderGenerator orderGenerator = new OrderGenerator();
        orderGenerator.generateOrder();
        System.out.println("Orders generated");
    }
}
