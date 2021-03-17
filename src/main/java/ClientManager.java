import entities.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class ClientManager {
    protected SessionFactory sessionFactory;

    protected void setup() {
        // configures settings from hibernate.cfg.xml
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        if (sessionFactory == null){
            System.err.println("    sessionFactory == null");
        }
        else {
            System.out.println("    sessionFactory != null");
        }
    }

    protected void exit() {
        sessionFactory.close();
    }

    protected void create() {
        Client client = new Client();
        client.setClient_name("Блохей Акула Изикеевич");
        client.setPhone("8-800-555-35-35");
        client.setIs_client_removed(false);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(client);

        session.getTransaction().commit();
        session.close();
    }

    protected void read() {
        Session session = sessionFactory.openSession();

        long bookId = 1;
        Client book = session.get(Client.class, bookId);

        System.out.println("Name: " + book.getClient_name());
        System.out.println("Phone: " + book.getPhone());
        System.out.println("client_is_removed: " + book.getIs_client_removed());

        session.close();
    }

    protected void update() {
        Client client = new Client();
        client.setClient_id(114);
        client.setPhone("+7-999-666-00-00");
        client.setClient_name("Бирюкова Андреана Михаиловна");
        client.setIs_client_removed(false);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(client);

        session.getTransaction().commit();
        session.close();
    }

    protected void delete() {
        Client client = new Client();
        client.setClient_id(114);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(client);

        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        // code to run the program
        ClientManager manager = new ClientManager();
        manager.setup();
        manager.create();
        manager.read();
//        manager.update();
//        manager.delete();
        manager.exit();
    }
}
