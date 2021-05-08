package dataAccess.order;

import dataAccess.film.Film;

import java.util.Date;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public boolean addOrder(Order order) {
        if (!order.checkCorrectness()) { return false;  }

        // save order only if there is available mediums
        Film film = order.getFilm();
        switch (order.getMedium()) {
            case "disc":
                int disk_number = film.getDisc_available_number();
                if (disk_number >= 1) {
                    film.setDisc_available_number(disk_number - 1);
                } else return false;
                break;

            case "cassette":
                int cassette_number = film.getCassette_available_number();
                if (cassette_number >= 1) {
                    film.setDisc_available_number(cassette_number - 1);
                } else return false;
                break;

            default:
                return false;
        }
        return this.orderDAO.save(order);
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
