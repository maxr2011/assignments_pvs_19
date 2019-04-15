package de.fhws.fiw.pvs.assignment_3.objects;

import de.fhws.fiw.pvs.assignment_3.json.Consts;

import java.time.LocalDate;
import java.util.Objects;

public class Person {

    // variables
    String firstName;
    String lastName;
    LocalDate birthDate;

    // constructor
    public Person(String firstName, String lastName, LocalDate birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        Objects.requireNonNull(birthDate);
        this.birthDate = birthDate;
    }

    // getter and setter
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        Objects.requireNonNull(birthDate);
        this.birthDate = birthDate;
    }

    // debug
    public String toString() {
        return  "Vorname: " + firstName + ", " +
                "Nachname: " + lastName + ", " +
                "Geburtsdatum: " + birthDate.toString() + ".";
    }

    public String toJSON() {
        return Consts.JSON_BRACKETS_OPEN +
                Consts.JSON_KEYVALUE_DECORATOR + "firstname" + Consts.JSON_KEYVALUE_DECORATOR +
                Consts.JSON_KEYVALUE_SEPERATOR +
                Consts.JSON_KEYVALUE_DECORATOR + firstName + Consts.JSON_KEYVALUE_DECORATOR +
                Consts.JSON_COMMA_SEPERATOR +
                Consts.JSON_KEYVALUE_DECORATOR + "lastname" + Consts.JSON_KEYVALUE_DECORATOR +
                Consts.JSON_KEYVALUE_SEPERATOR +
                Consts.JSON_KEYVALUE_DECORATOR + lastName + Consts.JSON_KEYVALUE_DECORATOR +
                Consts.JSON_COMMA_SEPERATOR +
                Consts.JSON_KEYVALUE_DECORATOR + "birthdate" + Consts.JSON_KEYVALUE_DECORATOR +
                Consts.JSON_KEYVALUE_SEPERATOR +
                Consts.JSON_KEYVALUE_DECORATOR + birthDate.toString() + Consts.JSON_KEYVALUE_DECORATOR +
                Consts.JSON_BRACKETS_CLOSE;
    }

}
