package dataAccess.order;

import java.util.Date;
import java.util.List;

public interface OrderDAO {

    boolean save(Order order);
    boolean deleteById(long id);
    boolean update(Order order);
    Order findById(long id);
    List<Order> loadAll();
    boolean returnFilmByOrderId(long id);
    List<Order> getOrdersOfClientForSpecifiedPeriod(long client_id, Date startDate, Date endDate);
    List<Order> getOrdersOfFilmForSpecifiedPeriod(long film_id, Date startDate, Date endDate);
    List<Order> getOrdersOfClientNotReturned(long client_id);
}
