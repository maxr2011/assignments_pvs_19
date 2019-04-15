package de.fhws.fiw.pvs.assignment_3.client;

import de.fhws.fiw.pvs.assignment_3.objects.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CRUDMain {

    public static void main(String[] args) {

        // 1. all crud operations will be tested here
        CRUDOperations.createPerson("Hans", "Mueller", LocalDate.of(1980, 12, 23));
        CRUDOperations.createPerson("Fredo", "Mueller", LocalDate.of(1988, 2, 3));
        CRUDOperations.createPerson("Bernd", "Meyer", LocalDate.of(1966, 5, 2));
        CRUDOperations.createPerson("Fridolin", "Meyer", LocalDate.of(1996, 10, 22));
        CRUDOperations.createPerson("Horst", "Berger", LocalDate.of(1992, 4, 5));
        System.out.println();

        // 2. get specific last name Persons
        PersonListOutput(CRUDOperations.getAllPersonsWithSpecificLastName("Mueller"));
        System.out.println();

        // 3. get all Persons
        PersonListOutput(CRUDOperations.getAllPersons());
        System.out.println();

        // 4. get Person who has a birth day within a start and end date
        PersonListOutput(CRUDOperations.getAllPersonsWithSpecificBirthDayRange(LocalDate.of(1980, 5, 23), LocalDate.of(1994, 3, 20)));

    }

    private static void PersonListOutput(List<Person> personList) {
        Objects.requireNonNull(personList);
        personList.forEach(person -> System.out.println("Person found: " + person.toString()));
    }

}
