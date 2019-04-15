package de.fhws.fiw.pvs.assignment_2.ex03.objects;

import java.io.Serializable;

public class B implements Serializable {

    private String previousLetter = "A";
    private String ascendingLetter = "C";

    public B(String a, String b) {
        previousLetter = a;
        ascendingLetter = b;
    }

    public String toString() {
        return "previousLetter: " + previousLetter + " - ascendingLetter: " + ascendingLetter;
    }

}
