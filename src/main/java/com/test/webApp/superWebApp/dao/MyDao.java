package com.test.webApp.superWebApp.dao;

import org.hibernate.Session;


public interface MyDao<T> {

    /* CRUD */
    T create(Session session, T created);
    T remove(Session session, Object key);
    T update(Session session, T updated);
    T update(Session session, String fieldName, Object newValue);
    T delete(Session session, Object key);


    /**
     * To obtain from the DB the entity associated with the specified Primary Key.
     *
     * @param session a Session.
     * @param key the Primary Key object of the Entity you are looking for.
     * @return the entity associated with the specified Primary Key.
     */
    T get(Session session, Object key);

    /**
     * Used to know whether or not a specific entity already exists within the DB.
     *
     * @param entityManager a Session.
     * @param clazz the Class of the Entity you are looking for.
     * @param key the Primary Key object of the Entity you are looking for.
     * @return {@code true} only if it exists. {@code false} otherwise,
     * but also if an Exception occured.
     */
    default boolean exists(Session entityManager, Class clazz, Object key) {
        try {
            /* If you use "getReference", the Proxy object needs to be accessed in order
            for the SQL Statement associated with the request to actually be executed. */
            return entityManager.find(clazz, key) != null;
        } catch (Exception ex) { // todo: use proper Exceptions
            ex.printStackTrace();
            return false;
        }
    }
}
