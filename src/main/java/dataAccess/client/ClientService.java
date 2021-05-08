package dataAccess.client;

import dataAccess.film.Film;

import java.util.Comparator;
import java.util.List;

public class ClientService {
    private final ClientDAO clientDAO = new ClientDAOImpl();

    public boolean addClient(Client client) {
        return this.clientDAO.save(client);
    }

    public boolean deleteClientById(long id) {
        return this.clientDAO.deleteById(id);
    }

    public boolean updateClient(Client client) {
        return this.clientDAO.update(client);
    }

    public Client findClientById(long id) {return this.clientDAO.findById(id);}

    public List<Client> loadAll() {return this.clientDAO.loadAll();}

    public List<Client> loadAllActualSorted() {
        List<Client> client = loadAll();
        client.removeIf(Client::getClient_is_removed);
        client.sort(Comparator.comparing(Client::getClient_name));
        return client;
    }
}
