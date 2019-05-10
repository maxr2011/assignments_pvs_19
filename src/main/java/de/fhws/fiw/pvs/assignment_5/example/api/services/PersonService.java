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

package de.fhws.fiw.pvs.assignment_5.example.api.services;

import de.fhws.fiw.pvs.assignment_5.example.api.states.persons.*;
import de.fhws.fiw.pvs.assignment_5.example.models.PersonModel;
import de.fhws.fiw.pvs.assignment_5.sutton.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.pvs.assignment_5.sutton.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "persons" )
public class PersonService extends AbstractService
{
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response getAllPersons( )
	{
		final GetAllPersons.GetAllPersonsQuery query = new GetAllPersons.GetAllPersonsQuery( );
		query.setPagingBehavior( new PagingBehaviorUsingOffsetSize( 0, 100 ) );

		return new GetAllPersons( ).setQuery( query )
								   .setUriInfo( this.uriInfo )
								   .setRequest( this.request )
								   .setHttpServletRequest( this.httpServletRequest )
								   .setContext( this.context )
								   .build( );
	}

	@GET
	@Path( "{id}" )
	@Produces( MediaType.APPLICATION_JSON )
	public Response getSinglePersons( @PathParam( "id" ) final long id )
	{
		return new GetSinglePerson( ).setRequestedId( id )
									 .setUriInfo( this.uriInfo )
									 .setRequest( this.request )
									 .setHttpServletRequest( this.httpServletRequest )
									 .setContext( this.context )
									 .build( );
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response createSinglePersons( final PersonModel personModel )
	{
		return new PostNewPerson( ).setModelToStore( personModel )
								   .setUriInfo( this.uriInfo )
								   .setRequest( this.request )
								   .setHttpServletRequest( this.httpServletRequest )
								   .setContext( this.context )
								   .build( );
	}

	@PUT
	@Path( "{id}" )
	@Consumes( MediaType.APPLICATION_JSON )
	public Response updateSinglePersons( @PathParam( "id" ) final long id, final PersonModel personModel )
	{
		personModel.setId( id );
		return new PutSinglePerson( ).setModelToUpdate( personModel )
									 .setUriInfo( this.uriInfo )
									 .setRequest( this.request )
									 .setHttpServletRequest( this.httpServletRequest )
									 .setContext( this.context )
									 .build( );
	}

	@DELETE
	@Path( "{id}" )
	@Consumes( MediaType.APPLICATION_JSON )
	public Response deleteSinglePersons( @PathParam( "id" ) final long id )
	{
		return new DeleteSinglePerson( ).setModelIdToDelete( id )
										.setUriInfo( this.uriInfo )
										.setRequest( this.request )
										.setHttpServletRequest( this.httpServletRequest )
										.setContext( this.context )
										.build( );
	}
}
