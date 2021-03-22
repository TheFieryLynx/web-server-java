package services;

import dataAccess.client.Client;
import dataAccess.client.ClientService;
import dataAccess.film.Film;
import dataAccess.film.FilmService;
import dataAccess.order.Order;
import dataAccess.order.OrderService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PrepareDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceSpecifiedReadTest {
    private OrderService order_s;

    @BeforeClass
    public void setUp() throws IOException, SQLException {
        PrepareDatabase.initEmptyDB();
        // Orders are related to clients and films.
        // Therefore, it is required to add values to these two tables
        ClientService client_s = new ClientService();
        for (Client client : TablesTestData.clients) {            client_s.addClient(client);        }
        FilmService film_s = new FilmService();
        for (Film film : TablesTestData.films) {            film_s.addFilm(film);        }
        this.order_s = new OrderService();
        for (Order order : TablesTestData.orders) {            order_s.addOrder(order);        }
    }

    @Test
    public void testGetOrdersOfClientForSpecifiedPeriod() {
        List<Order> ordersInPeriod = order_s.getOrdersOfClientForSpecifiedPeriod(
                2, java.sql.Date.valueOf("2012-01-01"), java.sql.Date.valueOf("2012-12-31"));
        Assert.assertEquals(ordersInPeriod.size(), 1);
        Order expectedOrder = new Order(TablesTestData.clients[1], TablesTestData.films[1], "cassette", 333,
                java.sql.Date.valueOf("2012-01-01"), java.sql.Date.valueOf("2012-02-01"));
        expectedOrder.setOrder_id(2);
        Assert.assertEquals(ordersInPeriod.get(0), expectedOrder);
    }

    @Test
    public void testGetOrdersOfClientNotReturned() {
        List<Order> ordersNotReturned = order_s.getOrdersOfClientNotReturned(2);
        Assert.assertEquals(ordersNotReturned.size(), 1);
        Order expectedOrder = new Order(TablesTestData.clients[1], TablesTestData.films[2], "cassette", 333,
                java.sql.Date.valueOf("2013-01-01"), null);
        expectedOrder.setOrder_id(3);
        Assert.assertEquals(ordersNotReturned.get(0), expectedOrder);
    }

}
