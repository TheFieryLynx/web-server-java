package dataAccess.order;

import dataAccess.GenericDAO_CRUD;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDAO extends GenericDAO_CRUD<Order> {

    public boolean returnFilmByOrderId(long id) {
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
        // todo check data not nul
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Order> q = session.createQuery("from Order where client.client_id = :clientId and " +
                ":sDate <= film_issue_date and film_issue_date <= :eDate", Order.class)
                .setParameter("clientId", client_id)
                .setParameter("sDate", startDate, TemporalType.DATE)
                .setParameter("eDate", endDate, TemporalType.DATE);
        q.getResultList();
        List<Order> orders = q.list();
        session.close();
        return orders;
    }

    public List<Order> getOrdersOfClientNotReturned(long client_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Order> q = session.createQuery(
                "from Order where client.client_id = :clientId and film_return_date is null", Order.class)
                .setParameter("clientId", client_id);
        q.getResultList();
        List<Order> orders = q.list();
        session.close();
        return orders;
    }
}
