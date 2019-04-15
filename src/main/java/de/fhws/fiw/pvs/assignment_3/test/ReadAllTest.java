package de.fhws.fiw.pvs.assignment_3.test;

import de.fhws.fiw.pvs.assignment_3.client.CRUDOperations;
import de.fhws.fiw.pvs.assignment_3.objects.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadAllTest {

    @Test
    public void testReadAll(){

        List<Person> personListTest = new ArrayList<>();

        // adding all persons directly to list
        personListTest.add(new Person("Hans", "Mueller", LocalDate.of(1980, 12, 23)));
        personListTest.add(new Person("Fredo", "Mueller", LocalDate.of(1988, 2, 3)));
        personListTest.add(new Person("Bernd", "Meyer", LocalDate.of(1966, 5, 2)));
        personListTest.add(new Person("Fridolin", "Meyer", LocalDate.of(1996, 10, 22)));
        personListTest.add(new Person("Horst", "Berger", LocalDate.of(1992, 4, 5)));

        // create
        personListTest.forEach(person -> CRUDOperations.createPerson(person));

        // read
        List<Person> personGetList = CRUDOperations.getAllPersons();

        // compare
        if(personGetList.size() == personListTest.size()) {
            for (int i = 0; i < personGetList.size(); i++) {
                assertEquals(personListTest.get(i).toString(), personGetList.get(i).toString());
            }
        } else {
            throw new IllegalStateException("Size of both lists are not equal.");
        }

    }

}
