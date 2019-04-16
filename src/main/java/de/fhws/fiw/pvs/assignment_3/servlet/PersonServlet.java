package de.fhws.fiw.pvs.assignment_3.servlet;

import de.fhws.fiw.pvs.assignment_3.objects.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "PersonServlet", urlPatterns = { "/personTest" } )
public class PersonServlet extends javax.servlet.http.HttpServlet
{
    public PersonManagement personManagement;

    public PersonServlet(){
        super();
        this.personManagement = new PersonManagementImpl();
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response ) throws IOException
    {
        List<Person> resultList;
        // 2 specific Person List (lastname)
        if(request.getParameterMap().containsKey("lastname")) {
            String lastname = request.getParameter("lastname");
            resultList = personManagement.getAllPersonsWithSpecificLastName(lastname);

        } // 4 specific Person List (birthday)
        else if(request.getParameterMap().containsKey("startdate") && request.getParameterMap().containsKey("enddate")) {
            LocalDate startDate = LocalDate.parse(request.getParameter("startdate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("enddate"));
            resultList = personManagement.getAllPersonsBirthDateRange(startDate, endDate);
        } // 3 complete person list
        else {
            resultList = personManagement.getAllPersons();
        }

        response.getWriter().print(JSONHelper.generateJSONFromList(resultList));
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response ) throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthdate"));

        Person person = personManagement.createPerson(firstName, lastName, birthDate);
        personManagement.savePerson(person);

        List<Person> personCreatedList = new ArrayList<>();
        personCreatedList.add(person);

        response.getWriter().print(JSONHelper.generateJSONFromList(personCreatedList));

    }

}
