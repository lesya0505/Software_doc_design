package tickets.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import tickets.rest.model.Order;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    Order findOrderById(Integer id);

    List<Order> findOrderByPrice(String price);

    @Transactional
    @Modifying
    void deleteById(Integer id);

    @Transactional
    @Modifying
    void deleteAll();
}
