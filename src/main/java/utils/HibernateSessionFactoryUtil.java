package utils;

import dataAccess.client.Client;

import dataAccess.film.Film;
import dataAccess.order.Order;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Film.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);  // todo сделать нормально
            }
        }
        if (sessionFactory == null){
            System.err.println("WARNING sessionFactory == null");  // todo сделать нормально
        }
        return sessionFactory;
    }
}

