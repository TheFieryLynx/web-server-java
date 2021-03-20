package services;

import entities.Client;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PrepareDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientServiceTest {
    private ClientService client_s;
    private final long nonExistentId = 100;
    private final long existentId = 1;
    private final Client clientUpdated =
            new Client("Толстая пчела", "8 800 700-06-11", false);

    @BeforeClass
    public void setUp() throws IOException, SQLException {
        PrepareDatabase.initEmptyDB();
        this.client_s = new ClientService();
    }

    @Test
    public void testClientService() {
        // fill the table
        for (Client client: TablesTestData.clients){
            Assert.assertTrue(client_s.addClient(client));
        }

        // read the whole table
        List<Client> clients = client_s.loadAll();
        Assert.assertEquals(clients.size(), TablesTestData.clients.length);
        for (int i =0; i < clients.size(); ++i){
            Assert.assertEquals(clients.get(i), TablesTestData.clients[i]);
        }

        // find by id & update
        Assert.assertNull(client_s.findClientById(nonExistentId));
        clientUpdated.setClient_id(nonExistentId);
        Assert.assertFalse(client_s.updateClient(clientUpdated));
        clientUpdated.setClient_id(existentId);
        Assert.assertTrue(client_s.updateClient(clientUpdated));
        Assert.assertEquals(clientUpdated, client_s.findClientById(existentId));

        // save object with initialized filed "id"
        Assert.assertFalse(client_s.addClient(clientUpdated));

        // remove all objects from the table
        for (Client client: clients){
            Assert.assertTrue(client_s.deleteClientById(client.getClient_id()));
        }
    }
}