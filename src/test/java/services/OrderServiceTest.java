package services;

import entities.Client;
import entities.Film;
import entities.Order;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PrepareDatabase;

import java.io.IOException;
import java.sql.SQLException;

public class OrderServiceTest {
    private OrderService order_s;

    @BeforeClass
    public void setUp() throws IOException, SQLException {
        PrepareDatabase.initEmptyDB();
        this.order_s = new OrderService();
        ClientService client_s = new ClientService();
        for (Client client: TablesTestData.clients){
            client_s.addClient(client);
        }
        FilmService film_s = new FilmService();
        for (Film film: TablesTestData.films){
            film_s.addFilm(film);
        }
    }

    @Test
    public void testAddOrder() {
        for (Order order: TablesTestData.orders){
            order_s.addOrder(order);
        }
    }
}