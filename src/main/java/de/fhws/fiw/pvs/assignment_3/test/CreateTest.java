package de.fhws.fiw.pvs.assignment_3.test;

import de.fhws.fiw.pvs.assignment_3.client.CRUDOperations;
import de.fhws.fiw.pvs.assignment_3.objects.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTest {

    @Test
    public void testCreate() {
        String firstName = "Maximilian";
        String lastName = "Rehberger";
        LocalDate birthDate = LocalDate.of(1995, 11, 20);
        // compare person to String
        assertEquals(new Person(firstName, lastName, birthDate).toString(),
                CRUDOperations.createPerson(firstName, lastName, birthDate).toString());
    }

}
