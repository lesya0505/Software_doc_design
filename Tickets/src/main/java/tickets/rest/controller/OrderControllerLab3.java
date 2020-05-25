package tickets.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tickets.business.OrderService;
import tickets.rest.model.Order;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order/try")
public class OrderControllerLab3 {
    private static final String SUCCESS = "Order was successfully deleted!";
    private static final String FAIL = "Failed to delete the order!";

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getOrders(Map<String, Object> model) {
        List<Order> orders = orderService.getAllOrders();
        model.put("orders", orders);
        return "order";
    }
    @GetMapping("/{orderId}")
    public String getOrderById(@PathVariable Integer orderId, Map<String, Object> model) {
        Order order = orderService.getOrderById(orderId);
        model.put("orders", order);
        return "order";
    }

    @PostMapping
    public String add(@RequestParam String username, @RequestParam String concertName,
                      @RequestParam String concertText, @RequestParam String price,
                      @RequestParam String seat, Map<String, Object> model) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String concertDate = formatter.format(new Date());
        Order order = new Order(username, concertName, concertText, price, concertDate, seat);
        orderService.createOrder(order);
        Iterable<Order> orders = orderService.getAllOrders();
        model.put("orders", orders);
        return "order";
    }


    @PostMapping("/filterByPrice")
    public String find(@RequestParam String filterPrice,
                       Map<String, Object> model) {
        List<Order> ordersByPrice= orderService.getOrderByPrice(filterPrice);
        model.put("orders", ordersByPrice);
        return "order";
    }

    @GetMapping("/put")
    public String getPut(Map<String, Object> model) {
        List<Order> orders = orderService.getAllOrders();
        model.put("updOrder", orders);
        return "putOrder";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, String concertText, String price,
                         String seat, Map<String, Object> model) {
        Order updatedOrder = orderService.updateOrder(concertText,price, seat, id);
        model.put("updOrder", updatedOrder);
        return "putOrder";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Map<String, Object> model) {
        try {
            orderService.deleteOrderById(id);
            System.out.println(SUCCESS);
            model.put("message", SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(FAIL);

            model.put("message", FAIL);
        }
        return "deleteOrder";
    }

    @GetMapping("/delete")
    public String getDelete(Map<String, Object> model) {
        model.put("message", " ");
        return "deleteOrder";
    }

    @PostMapping("delete")
    public String deleteOrder(@RequestParam Integer id, Map<String, Object> model) {
        orderService.deleteOrderById(id);
        model.put("message", SUCCESS);
        return "deleteOrder";
    }
}