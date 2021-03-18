package services;

import DAO.ClientDAO;
import entities.Client;

import java.util.List;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAO();

    public void addClient(Client client) {
        this.clientDAO.save(client);
    }

    public List<Client> loadAll() {return this.clientDAO.loadAll();}
}
