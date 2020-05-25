package tickets.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tickets.business.OrderService;
import tickets.rest.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/{id}")
    public Order getById(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping(path = "/price/{price}")
    public List<Order> getByPrice(@PathVariable("price") String price)
    {
        return orderService.getOrderByPrice(price);
    }

    @PostMapping
    public Order createOrder(String username, String concertName,
                             String concertText, String price, String seat) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String concertDate= formatter.format(new Date());
        Order order = new Order(username,concertName,
                concertText, price, concertDate, seat);
        orderService.createOrder(order);
        return order;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestParam String concertText, String price,
                             String seat, @PathVariable Integer id) {
        return orderService.updateOrder(concertText, price, seat, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        orderService.deleteOrderById(id);
        return "The order with id: " + id + " was  deleted!";
    }

    @DeleteMapping(path = "/delete/all")
    public String deleteAll() {
        orderService.deleteAllOrders();
        return "All records deleted!";
    }

    @RequestMapping(path = "/csvinput")
    public List<Order> inputDataToDb() {
        orderService.saveDataFromCsv();
        return orderService.getAllOrders();
    }
}
