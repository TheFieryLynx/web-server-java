package DAO;

import entities.Client;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ClientDAO {
    public Client findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public boolean save(Client client) {
        // session.save() implicitly reassign field "id". This is not expected behavior.
        // Therefore the method require an uninitialized field "id"
        if (client.getClient_id() >= 0) { return false; }
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.save(client);
        session.close();
        return true;
    }

    public boolean update(Client client) {
        // So. It is bad logic that I read an object from DB, do not use this object
        // and after reading I assign new object to DB.
        // But there is no hibernate's method which check existence of PR in the table.
        // On other hand I dont want to rewrite all fields of the first got object.
        if (findById(client.getClient_id()) == null){ return false; }
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(client);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public void delete(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(client);
        session.getTransaction().commit();
        session.close();
    }

    public boolean deleteById(long id){
        boolean result = false;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Object persistentInstance = session.load(Client.class, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
            result = true;
        }
        session.close();
        return result;
    }

    public List<Client> loadAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Client> criteria = session.getCriteriaBuilder().createQuery(Client.class);
        criteria.from(Client.class);
        List<Client> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}
