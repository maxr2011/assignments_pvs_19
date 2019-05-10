/*
 * Copyright 2019 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.fhws.fiw.pvs.assignment_5.zikzak.api.services;

import de.fhws.fiw.pvs.assignment_5.sutton.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.pvs.assignment_5.sutton.api.services.AbstractService;
import de.fhws.fiw.pvs.assignment_5.zikzak.api.states.users.*;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "users" )
public class UserService extends AbstractService
{
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response getAllUsers( )
	{
		final GetAllUsers.GetAllUsersQuery query = new GetAllUsers.GetAllUsersQuery( );
		query.setPagingBehavior( new PagingBehaviorUsingOffsetSize( 0, 100 ) );

		return new GetAllUsers( ).setQuery( query )
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
		return new GetSingleUser( ).setRequestedId( id )
									 .setUriInfo( this.uriInfo )
									 .setRequest( this.request )
									 .setHttpServletRequest( this.httpServletRequest )
									 .setContext( this.context )
									 .build( );
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response createSingleUsers( final UserModel userModel )
	{
		return new PostNewUser( ).setModelToStore( userModel )
								   .setUriInfo( this.uriInfo )
								   .setRequest( this.request )
								   .setHttpServletRequest( this.httpServletRequest )
								   .setContext( this.context )
								   .build( );
	}

	@PUT
	@Path( "{id}" )
	@Consumes( MediaType.APPLICATION_JSON )
	public Response updateSingleUsers( @PathParam( "id" ) final long id, final UserModel userModel )
	{
		userModel.setId( id );
		return new PutSingleUser( ).setModelToUpdate( userModel )
									 .setUriInfo( this.uriInfo )
									 .setRequest( this.request )
									 .setHttpServletRequest( this.httpServletRequest )
									 .setContext( this.context )
									 .build( );
	}

	@DELETE
	@Path( "{id}" )
	@Consumes( MediaType.APPLICATION_JSON )
	public Response deleteSingleUsers( @PathParam( "id" ) final long id )
	{
		return new DeleteSingleUser( ).setModelIdToDelete( id )
										.setUriInfo( this.uriInfo )
										.setRequest( this.request )
										.setHttpServletRequest( this.httpServletRequest )
										.setContext( this.context )
										.build( );
	}
}
