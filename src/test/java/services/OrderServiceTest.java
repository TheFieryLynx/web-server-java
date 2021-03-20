package services;

import entities.Client;
import entities.Film;
import entities.Order;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PrepareDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceTest {
    private OrderService order_s;
    private final long nonExistentId = 100;
    private final long existentId = 1;
    private final Order orderUpdated = new Order(1, 2, "disk", 50,
            java.sql.Date.valueOf("2008-11-01"), java.sql.Date.valueOf("2008-12-01"));

    @BeforeClass
    public void setUp() throws IOException, SQLException {
        PrepareDatabase.initEmptyDB();
        // Orders are related to clients and films.
        // Therefore, it is required to add values to these two tables
        ClientService client_s = new ClientService();
        for (Client client: TablesTestData.clients) { client_s.addClient(client); }
        FilmService film_s = new FilmService();
        for (Film film : TablesTestData.films) { film_s.addFilm(film); }
        this.order_s = new OrderService();
    }

    @Test
    public void testOrderService() {
        // fill the table
        for (Order order : TablesTestData.orders) {
            Assert.assertTrue(order_s.addOrder(order));
        }

        // read the whole table
        List<Order> orders = order_s.loadAll();
        Assert.assertEquals(orders.size(), TablesTestData.orders.length);
        for (int i = 0; i < orders.size(); ++i) {
            Assert.assertEquals(orders.get(i), TablesTestData.orders[i]);
        }

        // find by id & update
        Assert.assertNull(order_s.findOrderById(nonExistentId));
        orderUpdated.setOrder_id(nonExistentId);
        Assert.assertFalse(order_s.updateOrder(orderUpdated));
        orderUpdated.setOrder_id(existentId);
        Assert.assertTrue(order_s.updateOrder(orderUpdated));
        Assert.assertEquals(orderUpdated, order_s.findOrderById(existentId));

        // save object with initialized filed "id"
        Assert.assertFalse(order_s.addOrder(orderUpdated));

        // remove all objects from the table
        for (Order order : orders) {
            Assert.assertTrue(order_s.deleteOrderById(order.getOrder_id()));
        }
    }
}