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

package de.fhws.fiw.pvs.assignment_5.example.api.states.persons;

import de.fhws.fiw.pvs.assignment_5.example.database.DaoFactory;
import de.fhws.fiw.pvs.assignment_5.example.models.PersonModel;
import de.fhws.fiw.pvs.assignment_5.sutton.api.queries.AbstractQuery;
import de.fhws.fiw.pvs.assignment_5.sutton.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.pvs.assignment_5.sutton.database.DatabaseException;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.CollectionModelResult;

import javax.ws.rs.core.MediaType;
import java.util.function.Predicate;

public class GetAllPersons extends AbstractGetCollectionState<PersonModel>
{
	public GetAllPersons( )
	{
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( PersonUri.REL_PATH, PersonRelTypes.CREATE_PERSON, MediaType.APPLICATION_JSON );
	}

	public static class GetAllPersonsQuery extends AbstractQuery<PersonModel>
	{
		@Override protected CollectionModelResult<PersonModel> doExecuteQuery( ) throws DatabaseException
		{
			return DaoFactory.getInstance( ).getPersonDao( ).readByPredicate( all( ) );
		}
	}

	public static class FilterByNames extends AbstractQuery<PersonModel>
	{
		protected String firstName;

		protected String lastName;

		public FilterByNames( final String firstName, final String lastName )
		{
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override protected CollectionModelResult<PersonModel> doExecuteQuery( ) throws DatabaseException
		{
			return DaoFactory.getInstance( ).getPersonDao( ).readByPredicate( byFirstNameAndLastName( ) );
		}

		protected Predicate<PersonModel> byFirstNameAndLastName( )
		{
			return p -> p.getFirstName( ).equalsIgnoreCase( this.firstName ) &&
				p.getLastName( ).equalsIgnoreCase( this.lastName );
		}
	}
}
