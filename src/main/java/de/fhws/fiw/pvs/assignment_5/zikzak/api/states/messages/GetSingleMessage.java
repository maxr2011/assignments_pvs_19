package de.fhws.fiw.pvs.assignment_5.zikzak.api.states.messages;

import de.fhws.fiw.pvs.assignment_5.sutton.api.states.get.AbstractGetState;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.SingleModelResult;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.users.UserRelTypes;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.users.UserUri;
import de.fhws.fiw.pvs.assignment_5.zikzak.database.DaoFactory;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.MessageModel;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.UserModel;

import javax.ws.rs.core.MediaType;

public class GetSingleMessage extends AbstractGetState<MessageModel> {

    public GetSingleMessage( )
    {
    }

    @Override protected SingleModelResult<MessageModel> loadModel( )
    {
        return DaoFactory.getInstance( ).getMessageDao( ).readById( this.requestedId );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( MessageUri.REL_PATH_ID, MessageRelTypes.UPDATE_SINGLE_MESSAGE, MediaType.APPLICATION_JSON,
                this.requestedId );
        addLink( MessageUri.REL_PATH_ID, MessageRelTypes.DELETE_SINGLE_MESSAGE, MediaType.APPLICATION_JSON,
                this.requestedId );
    }
}
