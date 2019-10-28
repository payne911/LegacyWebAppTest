package com.test.webApp.superWebApp.servlet;

import com.test.webApp.superWebApp.model.Person;
import com.test.webApp.superWebApp.dao.PersonDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class BackendServlet extends HttpServlet {

    /* Used for the Hibernate connection with the Oracle DB. */
    private SessionFactory factory;


    @Override
    public void init() throws ServletException {

        /* Initializing the SessionFactory. */
        factory = new Configuration() // looks for "hibernate.properties" in src/main/resources
                .addAnnotatedClass(Person.class) // adding our @Entity classes
                .buildSessionFactory();


        /* [START] Populating the DB if it is empty. */
        PersonDao pDao = new PersonDao();
        Session session = factory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();

        Person frst = new Person("lol@bob.com", "Bob", "Crazy");
        Person scnd = new Person("amazing@wahoo.com", "Wahoo", "Amazing");
        Person thrd = new Person("something@something.com", "Something", "Something");

        // todo: move into a Service
        if(!pDao.exists(session, Person.class, frst.getEmail())) session.save(frst);
        if(!pDao.exists(session, Person.class, scnd.getEmail())) session.save(scnd);
        if(!pDao.exists(session, Person.class, thrd.getEmail())) session.save(thrd);

        tx.commit();
        session.close();
        /* [END] Populating the DB if it is empty. */
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PersonDao pDao = new PersonDao();
        Session session = factory.openSession();

        String redirectedUrl = "/index.jsp"; // default

        /* Checking for illegal operations. */
        String action = getParam(request, "form_action");
        if(!action.equals("add")) { // todo: Log this with log4j2
            getServletContext()
                    .getRequestDispatcher(redirectedUrl)
                    .forward(request, response);
            session.close();
            return; // abort
        }

        /* Getting the parameters from the form. */
        String email = getParam(request, "email");
        String fn    = getParam(request, "firstName");
        String ln    = getParam(request, "lastName");
        Person newP  = new Person(email, fn, ln);

        /* Saving or not the user in the database. */
        if(pDao.exists(session, Person.class, email)) {
            redirectedUrl = "/cannot.jsp";
        } else {
            // todo: move into a Service
            Transaction tx = session.getTransaction();
            tx.begin();
            session.save(newP);
            tx.commit();
            redirectedUrl = "/subscribed.jsp";
            request.setAttribute("person", newP);
        }
        session.close();

        getServletContext()
                .getRequestDispatcher(redirectedUrl)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // nothing.
    }

    private String getParam(HttpServletRequest req, String s) {
        return req.getParameter(s);
    }
}
