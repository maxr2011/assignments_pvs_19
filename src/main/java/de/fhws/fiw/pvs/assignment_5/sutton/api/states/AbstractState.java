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

package de.fhws.fiw.pvs.assignment_5.sutton.api.states;

import de.fhws.fiw.pvs.assignment_5.sutton.api.hyperlinks.Hyperlinks;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractState
{
	protected UriInfo uriInfo;

	protected HttpServletRequest httpServletRequest;

	protected Request request;

	protected ContainerRequestContext context;

	protected Response.ResponseBuilder responseBuilder;

	public AbstractState( )
	{
		this.responseBuilder = Response.ok( );
	}

	public UriInfo getUriInfo( )
	{
		return this.uriInfo;
	}

	public AbstractState setUriInfo( final UriInfo uriInfo )
	{
		this.uriInfo = uriInfo;
		return this;
	}

	public HttpServletRequest getHttpServletRequest( )
	{
		return this.httpServletRequest;
	}

	public AbstractState setHttpServletRequest( final HttpServletRequest httpServletRequest )
	{
		this.httpServletRequest = httpServletRequest;
		return this;
	}

	public Request getRequest( )
	{
		return this.request;
	}

	public AbstractState setRequest( final Request request )
	{
		this.request = request;
		return this;
	}

	public ContainerRequestContext getContext( )
	{
		return this.context;
	}

	public AbstractState setContext( final ContainerRequestContext context )
	{
		this.context = context;
		return this;
	}

	public Response.ResponseBuilder getResponseBuilder( )
	{
		return this.responseBuilder;
	}

	public void setResponseBuilder( final Response.ResponseBuilder responseBuilder )
	{
		this.responseBuilder = responseBuilder;
	}

	public final Response build( )
	{
		try
		{
			return buildInternal( );
		}
		catch ( final Exception e )
		{
			e.printStackTrace( );

			return this.responseBuilder.status( Response.Status.INTERNAL_SERVER_ERROR )
									   .build( );
		}
	}

	protected abstract Response buildInternal( );

	protected void configureState( )
	{

	}

	/**
	 * Add a link to the response builder. This method should be called by sub-classes during
	 * processing of the request, for example as part of method {@link #buildInternal()}.
	 *
	 * @param uriTemplate a template of an absolute URI
	 * @param relType     the relation type of this link
	 * @param params      parameters that are replaced in the given template
	 */
	protected final void addLink( final String uriTemplate,
		final String relType,
		final String mediaType,
		final Object... params )
	{
		Hyperlinks.addLink( this.uriInfo, this.responseBuilder, uriTemplate, relType, mediaType, params );
	}

	protected final void addLink( final String uriTemplate, final String relType, final Object... params )
	{
		Hyperlinks.addLink( this.uriInfo, this.responseBuilder, uriTemplate, relType, null, params );
	}

	protected final String getMediaTypeFromRequestHeader( )
	{
		return getMediaTypeFromRequestHeader( "Accept" );
	}

	protected final String getMediaTypeFromRequestHeader( final String headerName )
	{
		return this.httpServletRequest.getHeader( headerName );
	}
}
