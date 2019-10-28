package com.test.webApp.superWebApp.dao;

import org.hibernate.Session;

public interface MyDao {
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
