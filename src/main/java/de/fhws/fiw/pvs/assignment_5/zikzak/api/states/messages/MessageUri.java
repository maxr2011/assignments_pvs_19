package de.fhws.fiw.pvs.assignment_5.zikzak.api.states.messages;

import de.fhws.fiw.pvs.assignment_5.zikzak.Start;

public interface MessageUri {
    String PATH_ELEMENT = "messages";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}
