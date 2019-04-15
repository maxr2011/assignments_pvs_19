package de.fhws.fiw.pvs.assignment_3.servlet;

import de.fhws.fiw.pvs.assignment_3.objects.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonManagementImpl implements PersonManagement {

    private List<Person> personList;

    public PersonManagementImpl() {
        personList = new ArrayList<Person>();
    }

    // CRUD Operations Implementation

    // create
    public Person createPerson(String firstName, String lastName, LocalDate birthDate){
        return new Person(firstName, lastName, birthDate);
    }

    public void savePerson(Person person) {
        personList.add(person);
    }

    public void saveSpecificPersons(List<Person> specificPersonList) {
        personList.addAll(specificPersonList);
    }

    // read
    public Person getFirstPerson(String firstName) {
        return null;
    }

    public List<Person> getAllPersons() {
        return personList;
    }

    public List<Person> getAllPersonsWithSpecificFirstName(String firstName) {

        return personList
                .stream()
                .filter(person -> person.getFirstName().equals(firstName))
                .collect(Collectors.toList());

    }

    public List<Person> getAllPersonsWithSpecificLastName(String lastName) {

        return personList
                .stream()
                .filter(person -> person.getLastName().equals(lastName))
                .collect(Collectors.toList());

    }

    public List<Person> getAllPersonsBirthDateRange(LocalDate startDate, LocalDate endDate) {

        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        return personList
                .stream()
                .filter(person -> person.getBirthDate().isAfter(startDate))
                .filter(person -> person.getBirthDate().isBefore(endDate))
                .collect(Collectors.toList());

    }

    // update
    public void updatePersonFirstName(Person p, String firstName) {

        personList.stream()
                .filter(person -> person.equals(p))
                .forEach(person -> person.setFirstName(firstName));

    }

    public void updatePersonLastName(Person p, String lastName) {

        personList.stream()
                .filter(person -> person.equals(p))
                .forEach(person -> person.setLastName(lastName));

    }

    public void updatePersonBirthDate(Person p, LocalDate birthDate) {

        personList.stream()
                .filter(person -> person.equals(p))
                .forEach(person -> person.setBirthDate(birthDate));

    }

    // replace single person
    public void replacePerson(Person a, Person b) {
        personList.set(personList.indexOf(a), b);
    }


    // delete
    public void deletePerson(Person person) {
        personList.remove(person);
    }

    public void deleteSpecificPersons(List<Person> specificPersonList) {
        personList.removeAll(specificPersonList);
    }

    public void deletePersons() {
        personList.removeAll(personList);
    }

    // debug
    public void printAllPersons() {
        personList.forEach(person -> System.out.println(person.toString()));
    }

    public String toJSON() {
        return JSONHelper.generateJSONFromList(personList);
    }

}
