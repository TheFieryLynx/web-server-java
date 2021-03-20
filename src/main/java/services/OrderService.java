package services;

import DAO.OrderDAO;
import entities.Order;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();

    public boolean addOrder(Order order) { return this.orderDAO.save(order);}

    public boolean deleteOrderById(long id) { return this.orderDAO.deleteById(id); }

    public boolean updateOrder(Order order) { return this.orderDAO.update(order); }

    public Order findOrderById(long id) {return this.orderDAO.findById(id);}

    public List<Order> loadAll() {return this.orderDAO.loadAll();}
}
