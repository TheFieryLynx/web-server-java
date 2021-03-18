import entities.Client;
import entities.Film;
import entities.Order;
import services.ClientService;
import services.FilmService;
import services.OrderService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClientService client_s = new ClientService();
        Client client = new Client(
                66, "пипа", "66-66", false);
        client_s.addClient(client);

        OrderService order_s = new OrderService();
        Order order = new Order(
                2, 3, "cassette", 333, java.sql.Date.valueOf("2011-01-01"), java.sql.Date.valueOf("2012-01-01"));
        order_s.addOrder(order);

        FilmService film_s = new FilmService();
        Film film = new Film("цмок",
                                "жмяк-жмякыч",
                                2019,
                                22,
                                33,
                                11,
                                12,
                                101,
                                102,
                                false);
        film_s.addFilm(film);

        List<Client> clients = client_s.loadAll();
        for (Client c : clients){
            System.out.println(c.getClient_name());
        }
    }
}
