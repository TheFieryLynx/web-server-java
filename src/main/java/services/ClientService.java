package services;

import DAO.ClientDAO;
import entities.Client;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAO();

    public void addClient(Client client) {
        this.clientDAO.save(client);
    }
}
