package de.fhws.fiw.pvs.assignment_5.zikzak.api.states.messages;

import de.fhws.fiw.pvs.assignment_5.sutton.api.queries.AbstractQuery;
import de.fhws.fiw.pvs.assignment_5.sutton.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.pvs.assignment_5.sutton.database.DatabaseException;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.CollectionModelResult;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.users.UserRelTypes;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.users.UserUri;
import de.fhws.fiw.pvs.assignment_5.zikzak.database.DaoFactory;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.MessageModel;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.UserModel;

import javax.ws.rs.core.MediaType;
import java.util.function.Predicate;

public class GetAllMessages extends AbstractGetCollectionState<MessageModel> {

    public GetAllMessages( )
    {
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( MessageUri.REL_PATH, MessageRelTypes.CREATE_MESSAGE, MediaType.APPLICATION_JSON );
    }

    public static class GetAllMessagesQuery extends AbstractQuery<MessageModel>
    {
        @Override protected CollectionModelResult<MessageModel> doExecuteQuery( ) throws DatabaseException
        {
            return DaoFactory.getInstance( ).getMessageDao( ).readByPredicate( all( ) );
        }
    }

}
