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
import de.fhws.fiw.pvs.assignment_5.sutton.api.states.put.AbstractPutState;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.NoContentResult;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.SingleModelResult;

import javax.ws.rs.core.MediaType;

public class PutSinglePerson extends AbstractPutState<PersonModel>
{
	public PutSinglePerson( )
	{
	}

	@Override protected SingleModelResult<PersonModel> loadModel( )
	{
		return DaoFactory.getInstance( ).getPersonDao( ).readById( this.modelToUpdate.getId( ) );
	}

	@Override protected NoContentResult updateModel( )
	{
		return DaoFactory.getInstance( ).getPersonDao( ).update( this.modelToUpdate );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( PersonUri.REL_PATH_ID, PersonRelTypes.GET_SINGLE_PERSON, MediaType.APPLICATION_JSON,
			this.modelToUpdate.getId( ) );
	}
}
