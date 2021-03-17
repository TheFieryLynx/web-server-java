package services;

import DAO.OrderDAO;
import entities.Order;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public void addOrder(Order order) {
        this.orderDAO.save(order);
    }
}
