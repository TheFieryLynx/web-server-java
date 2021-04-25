package dataAccess.order;

import dataAccess.GenericDAO_CRUD;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl extends GenericDAO_CRUD<Order> implements OrderDAO{

    public boolean returnFilmByOrderId(long id) {
        // may be it was right to write this method only in service
        // but here I can call methods which make this function faster
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Order returningOrder = session.get(Order.class, id);
        if ((returningOrder == null) || (returningOrder.getFilm_return_date() != null)) {
            // there is no order with the given key or the order is already returned
            session.close();
            return false;
        }
        session.beginTransaction();
        returningOrder.setFilm_return_date(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        session.saveOrUpdate(returningOrder);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Order> getOrdersOfClientForSpecifiedPeriod(long client_id, Date startDate, Date endDate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Order> q = session.createQuery("select ord from Order ord " +
                "where ord.client.client_id = :clientId and " +
                ":sDate <= ord.film_issue_date and " +
                "ord.film_issue_date <= :eDate", Order.class)
                .setParameter("clientId", client_id)
                .setParameter("sDate", startDate, TemporalType.DATE)
                .setParameter("eDate", endDate, TemporalType.DATE);
        q.getResultList();
        List<Order> orders = q.list();
        session.close();
        return orders;
    }

    public List<Order> getOrdersOfFilmForSpecifiedPeriod(long film_id, Date startDate, Date endDate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        // todo: it is a workaround to avoid writing 3 SQL request
        if (startDate == null) { startDate = java.sql.Date.valueOf("0001-01-01"); }
        if (endDate == null) { endDate = java.sql.Date.valueOf("9999-12-12"); }

        Query<Order> q = session.createQuery("select ord from Order ord " +
                "where ord.film.film_id = :filmId and " +
                ":sDate <= ord.film_issue_date and " +
                "ord.film_issue_date <= :eDate", Order.class)
                .setParameter("filmId", film_id)
                .setParameter("sDate", startDate, TemporalType.DATE)
                .setParameter("eDate", endDate, TemporalType.DATE);
        q.getResultList();
        List<Order> orders = q.list();
        session.close();
        return orders;
    }

    public List<Order> getOrdersOfClientNotReturned(long client_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Order> q = session.createQuery("select ord from Order ord " +
                "where ord.client.client_id = :clientId and ord.film_return_date is null", Order.class)
                .setParameter("clientId", client_id);
        q.getResultList();
        List<Order> orders = q.list();
        session.close();
        return orders;
    }
}
