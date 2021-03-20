package services;

import DAO.OrderDAO;
import entities.Order;

import java.util.Date;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();

    public boolean addOrder(Order order) { return this.orderDAO.save(order);}

    public boolean deleteOrderById(long id) { return this.orderDAO.deleteById(id); }

    public boolean updateOrder(Order order) { return this.orderDAO.update(order); }

    public Order findOrderById(long id) { return this.orderDAO.findById(id); }

    public List<Order> loadAll() { return this.orderDAO.loadAll(); }

    public List<Order> getOrdersOfClientForSpecifiedPeriod(long client_id, Date startDate, Date endDate) {
        return this.orderDAO.getOrdersOfClientForSpecifiedPeriod(client_id, startDate, endDate);
    }

    public List<Order> getOrdersOfClientNotReturned(long client_id) {
        return this.orderDAO.getOrdersOfClientNotReturned(client_id);
    }
}
