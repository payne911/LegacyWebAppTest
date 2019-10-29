package com.test.webApp.superWebApp.servlet;

import com.test.webApp.superWebApp.HibernateUtil;
import com.test.webApp.superWebApp.model.Person;
import com.test.webApp.superWebApp.service.PersonService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class BackendServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        /* Populating the DB if it is empty. */
        Session session = HibernateUtil.getSessionFactory().openSession();
        PersonService pServ = new PersonService();
        pServ.initDb(session);
        session.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        /* Checking for illegal operations. */
        String action = request.getParameter("form_action");
        if(!action.equals("add")) { // todo: Log this with log4j2
            getServletContext()
                    .getRequestDispatcher("/index.jsp") // default
                    .forward(request, response);
            return; // abort
        }

        /* Initialize some variables. */
        PersonService personService = new PersonService();
        String redirectedUrl;

        /* Getting the parameters from the form. */
        String email = request.getParameter("email");
        String fn    = request.getParameter("firstName");
        String ln    = request.getParameter("lastName");
        Person newP  = new Person(email, fn, ln);

        /* Saving or not the user in the database. */
        if(personService.personExists(session, email)) {
            redirectedUrl = "/cannot.jsp";
        } else {
            personService.createPerson(session, email, fn, ln);
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
}
