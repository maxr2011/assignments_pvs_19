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

package de.fhws.fiw.pvs.assignment_5.example.api.states;

import de.fhws.fiw.pvs.assignment_5.example.api.states.persons.PersonRelTypes;
import de.fhws.fiw.pvs.assignment_5.example.api.states.persons.PersonUri;
import de.fhws.fiw.pvs.assignment_5.sutton.api.states.get.AbstractGetDispatcherState;

import javax.ws.rs.core.MediaType;

public class DispatcherState extends AbstractGetDispatcherState
{
	@Override protected void defineTransitionLinks( )
	{
		addLink( PersonUri.REL_PATH, PersonRelTypes.CREATE_PERSON, MediaType.APPLICATION_JSON );
		addLink( PersonUri.REL_PATH, PersonRelTypes.GET_ALL_PERSONS, MediaType.APPLICATION_JSON );
	}
}
