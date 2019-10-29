package com.test.webApp.superWebApp.service;

import com.test.webApp.superWebApp.dao.MyDao;
import com.test.webApp.superWebApp.dao.PersonDao;
import com.test.webApp.superWebApp.model.Person;
import org.hibernate.Session;


public class PersonService implements MyService<Person> {
    private PersonDao personDao = new PersonDao();


    public void initDb(Session session) {
        personDao.create(session, new Person("lol@bob.com", "Bob", "Crazy"));
        personDao.create(session, new Person("amazing@wahoo.com", "Wahoo", "Amazing"));
        personDao.create(session, new Person("something@something.com", "Something", "Something"));
    }

    public void createPerson(Session session, String email, String firstName, String lastName) {
        personDao.create(session, new Person(email, firstName, lastName));
    }

    public boolean personExists(Session session, String email) {
        return personDao.exists(session, Person.class, email);
    }

    @Override
    public MyDao<Person> getMainDao() {
        return personDao;
    }
}
