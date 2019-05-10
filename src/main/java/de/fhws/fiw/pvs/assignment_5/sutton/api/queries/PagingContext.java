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

package de.fhws.fiw.pvs.assignment_5.sutton.api.queries;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class PagingContext
{
	private final UriInfo uriInfo;

	private final Response.ResponseBuilder responseBuilder;

	private final String mediaType;

	public PagingContext( final UriInfo uriInfo,
		final Response.ResponseBuilder responseBuilder,
		final String mediaType )
	{
		this.uriInfo = uriInfo;
		this.responseBuilder = responseBuilder;
		this.mediaType = mediaType;
	}

	public UriInfo getUriInfo( )
	{
		return this.uriInfo;
	}

	public Response.ResponseBuilder getResponseBuilder( )
	{
		return this.responseBuilder;
	}

	public String getMediaType( )
	{
		return this.mediaType;
	}
}
