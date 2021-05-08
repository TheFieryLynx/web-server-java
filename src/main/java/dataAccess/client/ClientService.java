package dataAccess.client;

import dataAccess.order.Order;
import dataAccess.order.OrderDAO;
import dataAccess.order.OrderDAOImpl;

import java.util.Comparator;
import java.util.List;

public class ClientService {
    private final ClientDAO clientDAO = new ClientDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public boolean addClient(Client client) {
        return this.clientDAO.save(client);
    }

    public boolean deleteClientById(long id) {
        return this.clientDAO.deleteById(id);
    }

    public boolean safetyDeleteClientById(long id) {
        // delete the client only if there is no orders with them
        Client client = this.clientDAO.findById(id);
        if (client == null) { return true; }
        List<Order> orders = this.orderDAO.getOrdersOfClientForSpecifiedPeriod(id, null, null);
        if (orders.size() == 0) {
            clientDAO.delete(client);
            return true;
        }
        client.setClient_is_removed(true);
        return this.clientDAO.update(client);
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
