package tickets.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.dataaccess.OrderRepo;
import tickets.rest.model.Order;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order createOrder(Order order) {
       return  orderRepo.save(order);
    }

    public Order getOrderById(Integer id) {
        return orderRepo.findOrderById(id);
    }

    public List<Order> getOrderByPrice(String price) {
        return orderRepo.findOrderByPrice(price);
    }

    public void deleteOrderById(Integer id) {
        orderRepo.deleteById(id);
    }

    public void deleteAllOrders() {
        orderRepo.deleteAll();
    }

    public void saveDataFromCsv() {
        String line ="";
        try {
            BufferedReader br = new BufferedReader(new FileReader("Order.csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                Order order = new Order();
                order.setUsername(data[0]);
                order.setConcertName(data[1]);
                order.setConcertText(data[2]);
                order.setPrice(data[3]);
                order.setConcertDate(data[4]);
                order.setSeat(data[5]);
                orderRepo.save(order);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Order updateOrder(String concertText, String price, String seat, Integer id) {
        Order order = orderRepo.findOrderById(id);
        order.setSeat(seat);
        order.setPrice(price);
        order.setConcertText(concertText);
        return orderRepo.save(order);
    }
}