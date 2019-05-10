package de.fhws.fiw.pvs.assignment_5.zikzak.api.states.messages;

import de.fhws.fiw.pvs.assignment_5.sutton.api.states.post.AbstractPostState;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.NoContentResult;
import de.fhws.fiw.pvs.assignment_5.zikzak.database.DaoFactory;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.MessageModel;

public class PostNewMessage extends AbstractPostState<MessageModel> {

    public PostNewMessage( )
    {
    }

    @Override protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance( ).getMessageDao( ).create( this.modelToStore );
    }

    @Override protected void defineTransitionLinks( )
    {

    }

}
