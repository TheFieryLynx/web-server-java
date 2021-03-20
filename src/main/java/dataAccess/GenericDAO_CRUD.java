package dataAccess;

import dataAccess.EntityWithId;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;

// realisation of Create, Read, Update, Delete methods
public class GenericDAO_CRUD<T extends EntityWithId> {
    // type of genetic class
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")  // turn off warning of unchecked cast
    public GenericDAO_CRUD() {
        this.persistentClass =
                (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(persistentClass, id);
    }

    public List<T> loadAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<T> criteria = session.getCriteriaBuilder().createQuery(persistentClass);
        criteria.from(persistentClass);
        List<T> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }

    public boolean save(T entity) {
        // session.save() implicitly reassign field "id". This is not expected behavior.
        // Therefore the method require an uninitialized field "id"
        if (entity.receiveId() >= 0) { return false; }
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.save(entity);
        session.close();
        return true;
    }

    public boolean update(T entity) {
        // So. It is bad logic that I read an object from DB, do not use this object
        // and after reading I assign new object to DB.
        // But there is no hibernate's method which check existence of PR in the table.
        // On other hand I dont want to rewrite all fields of the first got object.
        if (findById(entity.receiveId()) == null) { return false; }
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public void delete(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public boolean deleteById(long id) {
        boolean result = false;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Object persistentInstance = session.load(persistentClass, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
            result = true;
        }
        session.close();
        return result;
    }
}
