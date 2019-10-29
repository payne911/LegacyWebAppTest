package com.test.webApp.superWebApp;

import com.test.webApp.superWebApp.model.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Used for the connection between the ORM (Hibernate) and the Oracle DB.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // todo: try-catch
        return new Configuration() // looks for "hibernate.properties" in src/main/resources
                .addAnnotatedClass(Person.class) // adding our @Entity classes
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
