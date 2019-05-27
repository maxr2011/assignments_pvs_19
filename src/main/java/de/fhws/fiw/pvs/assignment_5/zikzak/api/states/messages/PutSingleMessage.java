package de.fhws.fiw.pvs.assignment_5.zikzak.api.states.messages;

import de.fhws.fiw.pvs.assignment_5.sutton.api.states.put.AbstractPutState;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.NoContentResult;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.SingleModelResult;
import de.fhws.fiw.pvs.assignment_5.zikzak.database.DaoFactory;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.MessageModel;

import javax.ws.rs.core.MediaType;

public class PutSingleMessage extends AbstractPutState<MessageModel> {

    public PutSingleMessage( )
    {
    }

    @Override protected SingleModelResult<MessageModel> loadModel( )
    {
        return DaoFactory.getInstance( ).getMessageDao().readById( this.modelToUpdate.getId( ) );
    }

    @Override protected NoContentResult updateModel( )
    {
        return DaoFactory.getInstance( ).getMessageDao( ).update( this.modelToUpdate );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( MessageUri.REL_PATH_ID, MessageRelTypes.GET_SINGLE_MESSAGE, MediaType.APPLICATION_JSON,
                this.modelToUpdate.getId( ) );
    }

}
