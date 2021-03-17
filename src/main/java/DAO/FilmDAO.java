package DAO;

import entities.Film;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class FilmDAO {
    public void save(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(film);
        tx1.commit();
        session.close();
    }

    public void update(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(film);
        tx1.commit();
        session.close();
    }

    public void delete(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(film);
        tx1.commit();
        session.close();
    }
}
