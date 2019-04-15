package de.fhws.fiw.pvs.assignment_3.client;

import de.fhws.fiw.pvs.assignment_3.objects.Person;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

public class CRUDOperations {

    // 1. For creating a person on the server, use a POST request that
    // expects all attributes in the body of the HTTP message.
    public static Person createPerson(String firstName, String lastName, LocalDate birthDate) {
        try {
            String urlParameters =  "firstname=" + firstName + "&" +
                                    "lastname=" + lastName + "&" +
                                    "birthdate=" + birthDate.toString();
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            String request = "http://localhost:8080/person/personTest";
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }

            final InputStream inputStream = conn.getInputStream( );
            String responseData = IOUtils.toString( inputStream );

            Person createdPerson = JSONHelper.getPersonListFromJSON(responseData).get(0);

            System.out.println(  "Server created Person: [" + createdPerson.toString() +"]");

            return createdPerson;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Person createPerson(Person person){
        return createPerson(person.getFirstName(), person.getLastName(), person.getBirthDate());
    }

    // 3. If no query parameters are given, the server should return all persons.
    public static List<Person> getAllPersons() {
        try {
            final URL uri = new URL("http://localhost:8080/person/personTest");
            final HttpURLConnection con = (HttpURLConnection) uri.openConnection();

            con.setDoInput(true);
            con.setRequestMethod("GET");

            final InputStream inputStream = con.getInputStream();
            String responseData = IOUtils.toString( inputStream );

            System.out.println("Server returned this list:");
            System.out.println("JSON: " + responseData);

            return JSONHelper.getPersonListFromJSON(responseData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2. The servlet should provide one method for handling all GET requests. If the URL
    // contains query parameters, the server should return a altered list as result.
    public static List<Person> getAllPersonsWithSpecificLastName(String lastName) {
        try {
            final URL uri = new URL("http://localhost:8080/person/personTest?lastname=" + lastName);
            final HttpURLConnection con = (HttpURLConnection) uri.openConnection();

            con.setDoInput(true);
            con.setRequestMethod("GET");

            final InputStream inputStream = con.getInputStream();
            String responseData = IOUtils.toString( inputStream );

            System.out.println("Server returned this list for lastname search \"" + lastName + "\":");
            System.out.println("JSON: " + responseData);

            return JSONHelper.getPersonListFromJSON(responseData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. return persons with specific birthday
    public static List<Person> getAllPersonsWithSpecificBirthDayRange(LocalDate start, LocalDate end) {
        try {
            final URL uri = new URL("http://localhost:8080/person/personTest?startdate=" + start.toString() + "&enddate=" + end.toString());
            final HttpURLConnection con = (HttpURLConnection) uri.openConnection();

            con.setDoInput(true);
            con.setRequestMethod("GET");

            final InputStream inputStream = con.getInputStream();
            String responseData = IOUtils.toString( inputStream );

            System.out.println("Server returned this list for birthday search (between "+start.toString() + " and " + end.toString() + "): ");
            System.out.println("JSON: " + responseData);

            return JSONHelper.getPersonListFromJSON(responseData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
