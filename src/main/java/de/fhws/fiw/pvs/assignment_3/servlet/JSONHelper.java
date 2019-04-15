package de.fhws.fiw.pvs.assignment_3.servlet;

import de.fhws.fiw.pvs.assignment_3.json.Consts;
import de.fhws.fiw.pvs.assignment_3.objects.Person;

import java.util.List;

public class JSONHelper {

    // json object wrapper
    public static String generateJSONFromList(List<Person> specificPersonList) {

        isNotEmpty(specificPersonList);

        String JSON = Consts.JSON_BRACKETS_OPEN;

        JSON += Consts.JSON_KEYVALUE_DECORATOR + "personlist" + Consts.JSON_KEYVALUE_DECORATOR +
                Consts.JSON_KEYVALUE_SEPERATOR;

        JSON += Consts.JSON_OBJECT_BRACKETS_OPEN;

        int i = 0;
        for(Person p : specificPersonList) {
            JSON += p.toJSON();
            i++;
        }

        JSON += Consts.JSON_OBJECT_BRACKETS_CLOSE;

        JSON += Consts.JSON_BRACKETS_CLOSE;

        return JSON;
    }

    public static void isNotEmpty(List<Person> objs) {
        if(objs.isEmpty()) {
            throw new IllegalStateException("List is empty!");
        }
    }

}
