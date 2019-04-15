package de.fhws.fiw.pvs.assignment_3.client;

import de.fhws.fiw.pvs.assignment_3.json.Consts;
import de.fhws.fiw.pvs.assignment_3.objects.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JSONHelper {

    public static List<Person> getPersonListFromJSON(String JSON) {

        List<Person> jsonResultList = new ArrayList<>();

        if(JSON.substring(2, 12).equals("personlist")) {

            String PersonListObject = JSON.substring(14, JSON.lastIndexOf("]")+1);
            List<Person> persons = new ArrayList<>();

            boolean write = false;
            String tempPerson = "";
            for(char c : PersonListObject.toCharArray()) {
                if (c == Consts.JSON_BRACKETS_OPEN_CHAR) {
                    write = true;
                    continue;
                }
                if (c == Consts.JSON_BRACKETS_CLOSE_CHAR) {
                    write = false;
                    if(tempPerson.equals("")) break;
                    persons.add(getPersonFromString(tempPerson));
                    tempPerson = "";
                }
                if (write) {
                    tempPerson += "" + c + "";
                }
            }

            return persons;

        } else {
            throw new IllegalStateException("no personlist json");
        }

    }

    public static Person getPersonFromString(String person) {
        String[] personSplit = person.split(Consts.JSON_COMMA_SEPERATOR);
        if(personSplit.length != 3) {
            throw new IllegalStateException("Person String length invalid");
        }
        return new Person(checkAndGetValueFromString(personSplit[0], "firstname"),
                checkAndGetValueFromString(personSplit[1], "lastname"),
                LocalDate.parse(checkAndGetValueFromString(personSplit[2], "birthdate")));
    }

    public static String checkAndGetValueFromString(String string, String checkKey) {
        String[] valueSplit = string.split(Consts.JSON_KEYVALUE_SEPERATOR);
        String key = valueSplit[0].replace(Consts.JSON_KEYVALUE_DECORATOR, "");
        String value = valueSplit[1].replace(Consts.JSON_KEYVALUE_DECORATOR, "");
        if(key.equals(checkKey)) {
            return value;
        } else {
            throw new IllegalStateException("Invalid key.");
        }
    }

}
