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

package de.fhws.fiw.pvs.assignment_5.sutton.database.inmemory;



import de.fhws.fiw.pvs.assignment_5.sutton.database.results.CollectionModelResult;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.NoContentResult;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.SingleModelResult;
import de.fhws.fiw.pvs.assignment_5.sutton.models.AbstractModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractInMemoryStorage<T extends AbstractModel>
{
	protected Map<Long, T> storage;

	private final AtomicLong nextId;

	protected AbstractInMemoryStorage( )
	{
		this.storage = new HashMap<>( );
		this.nextId = new AtomicLong( 1l );
	}

	public NoContentResult create(final T model )
	{
		model.setId( nextId( ) );
		this.storage.put( model.getId( ), model );
		return new NoContentResult( );
	}

	public SingleModelResult<T> readById(final long id )
	{
		if ( this.storage.containsKey( id ) )
		{
			return new SingleModelResult<>( this.storage.get( id ) );
		}
		else
		{
			return new SingleModelResult<>( );
		}
	}

	public CollectionModelResult<T> readByPredicate(final Predicate<T> predicate )
	{
		return new CollectionModelResult( filterBy( predicate ) );
	}

	private Collection<T> filterBy( final Predicate<T> predicate )
	{
		return this.storage.values( )
						   .stream( )
						   .filter( predicate )
						   .collect( Collectors.toList( ) );
	}

	public NoContentResult update( final T model )
	{
		this.storage.put( model.getId( ), model );
		return new NoContentResult( );
	}

	public NoContentResult delete( final long id )
	{
		this.storage.remove( id );
		return new NoContentResult( );
	}

	private final long nextId( )
	{
		return this.nextId.getAndIncrement( );
	}

}
