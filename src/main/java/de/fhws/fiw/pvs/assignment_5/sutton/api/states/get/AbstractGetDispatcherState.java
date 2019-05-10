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

package de.fhws.fiw.pvs.assignment_5.sutton.api.states.get;



import de.fhws.fiw.pvs.assignment_5.sutton.api.caching.CachingUtils;
import de.fhws.fiw.pvs.assignment_5.sutton.api.hyperlinks.Hyperlinks;
import de.fhws.fiw.pvs.assignment_5.sutton.api.states.AbstractState;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public abstract class AbstractGetDispatcherState extends AbstractState
{
	protected AbstractGetDispatcherState( )
	{
		super( );
	}

	@Override
	protected Response buildInternal( )
	{
		configureState( );

		return createResponse( );
	}

	protected Response createResponse( )
	{
		defineHttpResponseBody( );

		defineSelfLink( );

		defineTransitionLinks( );

		this.responseBuilder.cacheControl( CachingUtils.create60SecondsPublicCaching( ) );

		return this.responseBuilder.build( );
	}

	private void defineHttpResponseBody( )
	{
		this.responseBuilder.entity( "" );
	}

	protected abstract void defineTransitionLinks( );

	protected void defineSelfLink( )
	{
		final UriBuilder builder = this.uriInfo.getAbsolutePathBuilder( );
		final URI self = builder.build( );

		Hyperlinks.addLink( this.responseBuilder, self, "self", getMediaTypeFromRequestHeader( ) );
	}
}
