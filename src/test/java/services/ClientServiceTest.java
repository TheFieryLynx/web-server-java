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

    @BeforeClass
    public void setUp() throws IOException, SQLException {
        PrepareDatabase.initDB();
        this.client_s = new ClientService();
    }

    @Test
    public void testAddClient() {
        for (Client client: TablesTestData.clients){
            client_s.addClient(client);
        }
    }

    @Test
    public void testLoadAll() {
        List<Client> clients = client_s.loadAll();
        Assert.assertEquals(clients.size(), TablesTestData.clients.length);
        for (int i =0; i < clients.size(); ++i){
            Assert.assertEquals(clients.get(i), TablesTestData.clients[i]);
        }
    }
}