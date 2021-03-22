package dataAccess.order;

import java.util.Date;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public boolean addOrder(Order order) {
        if (order.checkCorrectness()) { return this.orderDAO.save(order); }
        return false;
    }

    public boolean deleteOrderById(long id) { return this.orderDAO.deleteById(id); }

    public boolean updateOrder(Order order) {
        if (order.checkCorrectness()) { return this.orderDAO.update(order); }
        return false;
    }

    public boolean returnFilmByOrderId(long id) {
        return this.orderDAO.returnFilmByOrderId(id);
    }

    public Order findOrderById(long id) { return this.orderDAO.findById(id); }

    public List<Order> loadAll() { return this.orderDAO.loadAll(); }

    public List<Order> getOrdersOfClientForSpecifiedPeriod(long client_id, Date startDate, Date endDate) {
        return this.orderDAO.getOrdersOfClientForSpecifiedPeriod(client_id, startDate, endDate);
    }

    public List<Order> getOrdersOfFilmForSpecifiedPeriod(long film_id, Date startDate, Date endDate) {
        return this.orderDAO.getOrdersOfFilmForSpecifiedPeriod(film_id, startDate, endDate);
    }
    public List<Order> getOrdersOfClientNotReturned(long client_id) {
        return this.orderDAO.getOrdersOfClientNotReturned(client_id);
    }
}
