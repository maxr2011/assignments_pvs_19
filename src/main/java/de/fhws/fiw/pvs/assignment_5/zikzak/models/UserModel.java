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

package de.fhws.fiw.pvs.assignment_5.zikzak.models;

import com.owlike.genson.annotation.JsonConverter;
import com.owlike.genson.annotation.JsonIgnore;
import de.fhws.fiw.pvs.assignment_5.sutton.api.converter.LinkConverter;
import de.fhws.fiw.pvs.assignment_5.sutton.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import java.time.LocalDate;

public class UserModel extends AbstractModel
{
	protected String firstName;

	protected String lastName;

	protected LocalDate dateOfBirth;

	@InjectLink( style = InjectLink.Style.ABSOLUTE, value = "users/${instance.id}",
		type = "application/json", rel = "self" )
	protected Link selfUri;

	public String getFirstName( )
	{
		return this.firstName;
	}

	public void setFirstName( final String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName( )
	{
		return this.lastName;
	}

	public void setLastName( final String lastName )
	{
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth( )
	{
		return this.dateOfBirth;
	}

	public void setDateOfBirth( final LocalDate dateOfBirth )
	{
		this.dateOfBirth = dateOfBirth;
	}

	@JsonConverter( LinkConverter.class )
	public Link getSelfUri( )
	{
		return this.selfUri;
	}

	@JsonIgnore
	public void setSelfUri( final Link selfUri )
	{
		this.selfUri = selfUri;
	}
}
