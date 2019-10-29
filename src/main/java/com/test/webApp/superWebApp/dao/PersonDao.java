package com.test.webApp.superWebApp.dao;

import com.test.webApp.superWebApp.model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class PersonDao implements MyDao<Person> {

    @Override
    public Person get(Session session, Object key) {
        if (!exists(session, Person.class, key))
            return null;
        return session.find(Person.class, key);
    }

//    @Override
//    public boolean verify(Object... fields) {
//        if(fields.length != 3)
//            return false;
//        return Arrays.stream(fields).allMatch(String.class::isInstance);
//    }

    @Override
    public Person create(Session session, Person created) {

        /* Instantiate the object to verify existence. */
        if (exists(session, Person.class, created.getEmail()))
            return null;

        /* If it doesn't exist, create it! */
        Transaction tx = session.beginTransaction();
        session.save(created);
        tx.commit();

        return created;
    }

    @Override
    public Person remove(Session session, Object key) {
        throw new UnsupportedOperationException("WIP");
    }

    @Override
    public Person update(Session session, Person updated) {
        throw new UnsupportedOperationException("WIP");
    }

    @Override
    public Person update(Session session, String fieldName, Object newValue) {
        throw new UnsupportedOperationException("WIP");
    }

    @Override
    public Person delete(Session session, Object key) {
        throw new UnsupportedOperationException("WIP");
    }
}
