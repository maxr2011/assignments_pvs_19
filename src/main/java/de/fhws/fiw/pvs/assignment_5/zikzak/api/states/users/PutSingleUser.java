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



import de.fhws.fiw.pvs.assignment_5.sutton.api.states.put.AbstractPutState;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.NoContentResult;
import de.fhws.fiw.pvs.assignment_5.sutton.database.results.SingleModelResult;
import de.fhws.fiw.pvs.assignment_5.zikzak.database.DaoFactory;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.UserModel;

import javax.ws.rs.core.MediaType;

public class PutSingleUser extends AbstractPutState<UserModel>
{
	public PutSingleUser( )
	{
	}

	@Override protected SingleModelResult<UserModel> loadModel( )
	{
		return DaoFactory.getInstance( ).getUserDao( ).readById( this.modelToUpdate.getId( ) );
	}

	@Override protected NoContentResult updateModel( )
	{
		return DaoFactory.getInstance( ).getUserDao( ).update( this.modelToUpdate );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( UserUri.REL_PATH_ID, UserRelTypes.GET_SINGLE_USER, MediaType.APPLICATION_JSON,
			this.modelToUpdate.getId( ) );
	}
}
