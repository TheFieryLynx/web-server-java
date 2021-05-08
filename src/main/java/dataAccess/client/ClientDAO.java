package dataAccess.client;


import dataAccess.film.Film;

import java.util.List;

public interface ClientDAO {

    boolean save(Client client);
    void delete(Client client);
    boolean deleteById(long id);
    boolean update(Client client);
    Client findById(long id);
    List<Client> loadAll();
}
