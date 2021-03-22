package dataAccess.client;


import java.util.List;

public interface ClientDAO {

    boolean save(Client client);
    boolean deleteById(long id);
    boolean update(Client client);
    Client findById(long id);
    List<Client> loadAll();
}
