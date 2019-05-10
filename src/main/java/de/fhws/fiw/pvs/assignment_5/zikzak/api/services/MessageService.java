package de.fhws.fiw.pvs.assignment_5.zikzak.api.services;

import de.fhws.fiw.pvs.assignment_5.sutton.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.pvs.assignment_5.sutton.api.services.AbstractService;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.messages.GetAllMessages;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.messages.GetSingleMessage;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.messages.PostNewMessage;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.users.GetAllUsers;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.MessageModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("messages")
public class MessageService extends AbstractService {

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getAllMessages()
    {
        final GetAllMessages.GetAllMessagesQuery query = new GetAllMessages.GetAllMessagesQuery( );
        query.setPagingBehavior( new PagingBehaviorUsingOffsetSize( 0, 100 ) );

        return new GetAllMessages( ).setQuery( query )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( );
    }

    @GET
    @Path( "{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getSingleUsers( @PathParam( "id" ) final long id )
    {
        return new GetSingleMessage( ).setRequestedId( id )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( );
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    public Response createSingleMessage( final MessageModel messageModel )
    {
        return new PostNewMessage( ).setModelToStore( messageModel )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( );
    }

}
