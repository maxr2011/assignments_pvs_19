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

package de.fhws.fiw.pvs.assignment_5.zikzak.api.states.users;

import de.fhws.fiw.pvs.assignment_5.sutton.api.queries.AbstractQuery;
import de.fhws.fiw.pvs.assignment_5.sutton.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.pvs.assignment_5.sutton.database.DatabaseException;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.CollectionModelResult;
import de.fhws.fiw.pvs.assignment_5.zikzak.database.DaoFactory;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.UserModel;

import javax.ws.rs.core.MediaType;
import java.util.function.Predicate;

public class GetAllUsers extends AbstractGetCollectionState<UserModel>
{
	public GetAllUsers( )
	{
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( UserUri.REL_PATH, UserRelTypes.CREATE_USER, MediaType.APPLICATION_JSON );
	}

	public static class GetAllUsersQuery extends AbstractQuery<UserModel>
	{
		@Override protected CollectionModelResult<UserModel> doExecuteQuery( ) throws DatabaseException
		{
			return DaoFactory.getInstance( ).getUserDao( ).readByPredicate( all( ) );
		}
	}

	public static class FilterByNames extends AbstractQuery<UserModel>
	{
		protected String firstName;

		protected String lastName;

		public FilterByNames( final String firstName, final String lastName )
		{
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override protected CollectionModelResult<UserModel> doExecuteQuery( ) throws DatabaseException
		{
			return DaoFactory.getInstance( ).getUserDao( ).readByPredicate( byFirstNameAndLastName( ) );
		}

		protected Predicate<UserModel> byFirstNameAndLastName( )
		{
			return p -> p.getFirstName( ).equalsIgnoreCase( this.firstName ) &&
				p.getLastName( ).equalsIgnoreCase( this.lastName );
		}
	}
}
