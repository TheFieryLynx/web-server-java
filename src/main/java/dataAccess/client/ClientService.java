package dataAccess.client;

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
}
