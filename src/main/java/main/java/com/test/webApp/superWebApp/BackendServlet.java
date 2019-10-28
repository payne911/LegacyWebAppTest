package main.java.com.test.webApp.superWebApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BackendServlet extends HttpServlet {

    private List<Person> fakeDatabase = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        Person frst = new Person("lol@bob.com", "Bob", "Crazy");
        Person scnd = new Person("amazing@wahoo.com", "Wahoo", "Amazing");
        Person thrd = new Person("something@something.com", "Something", "Something");

        fakeDatabase.add(frst);
        fakeDatabase.add(scnd);
        fakeDatabase.add(thrd);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> reqParamMap = request.getParameterMap();

        String action = reqParamMap.get("action")[0];
        if(!action.equals("add")) // todo: how to log ?
            throw new ServletException("The specified action doesn't exist");

        getServletContext()
                .getRequestDispatcher("/index.jsp") // default
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String redirectedUrl = "/index.jsp"; // default

        String action = getParam(request, "action");
        if(!action.equals("add")) { // todo: how to log ?
            getServletContext()
                    .getRequestDispatcher(redirectedUrl)
                    .forward(request, response);
            return; // abort
        }

        // todo: find how to use ENUM to ensure the String is right everywhere! (index.jsp)
        String email = getParam(request, "email");
        String fn    = getParam(request, "firstName");
        String ln    = getParam(request, "lastName");
        Person newP  = new Person(email, fn, ln);

        boolean alreadyExists = fakeDatabase.stream().anyMatch(p -> p.equals(newP));
        if(alreadyExists) {
            redirectedUrl = "/cannot.jsp";
        } else {
            fakeDatabase.add(newP);
            redirectedUrl = "/subscribed.jsp";
            request.setAttribute("person", newP);
        }

        getServletContext()
                .getRequestDispatcher(redirectedUrl)
                .forward(request, response);
    }

    private String getParam(HttpServletRequest req, String s) {
        return req.getParameter(s);
    }
}
