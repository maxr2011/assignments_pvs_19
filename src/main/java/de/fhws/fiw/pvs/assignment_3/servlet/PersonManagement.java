package de.fhws.fiw.pvs.assignment_3.servlet;

import de.fhws.fiw.pvs.assignment_3.objects.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonManagement {

    // CRUD

    // create operations
    Person createPerson(String firstName, String lastName, LocalDate birthDate);
    void savePerson(Person person);
    void saveSpecificPersons(List<Person> specificPersonList);

    // read operations

    // single read
    Person getFirstPerson(String firstName);

    // multiple read
    List<Person> getAllPersons();
    List<Person> getAllPersonsWithSpecificFirstName(String firstName);
    List<Person> getAllPersonsWithSpecificLastName(String lastName);
    List<Person> getAllPersonsBirthDateRange(LocalDate startDate, LocalDate endDate);

    // update operations
    void updatePersonFirstName(Person person, String firstName);
    void updatePersonLastName(Person person, String lastName);
    void updatePersonBirthDate(Person person, LocalDate birthDate);

    // replace operations
    void replacePerson(Person a, Person b);

    // delete operations
    void deletePerson(Person person);
    void deleteSpecificPersons(List<Person> specificPersonList);
    void deletePersons();

    // debug
    void printAllPersons();
    String toJSON();

}
