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
import sun.plugin2.message.Message;

import javax.ws.rs.core.Link;
import java.time.LocalDate;
import java.util.List;

public class
UserModel extends AbstractModel
{
	protected String firstName;

	protected String lastName;

	protected LocalDate dateOfBirth;

	@InjectLink( style = InjectLink.Style.ABSOLUTE, value = "users/${instance.id}", type = "application/json", rel = "self" )
	protected Link selfUri;	// self identifier

	protected List<MessageModel> userMessages;

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

	// getter and setter
	public List<MessageModel> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<MessageModel> userMessages) {
		this.userMessages = userMessages;
	}

	// some crud operations for messsages
	public void addMessage(MessageModel message) {
		this.userMessages.add(message);
	}

	public void addMessageText(String messageText) {
		MessageModel newMessage = new MessageModel();
		newMessage.setMessageText(messageText);
		newMessage.setUserId(id);
		this.userMessages.add(newMessage);
	}

	public List<MessageModel> getAllMessages() {
		return userMessages;
	}

	public MessageModel getSingleMessage(int id) {
		return userMessages.get(id);
	}

	public MessageModel getSingleMessageByText(String messageText) {
		return userMessages.stream()
				.filter(message -> message.getMessageText().equals(messageText))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("no message found with message text '" + messageText + "'"));
	}

	public void updateSingleMessageById(int messageId, String messageText) {
		userMessages.get(messageId).setMessageText(messageText);
	}

	public void updateSingleMessageByText(String oldMessageText, String newMessageText) {
		getSingleMessageByText(oldMessageText).setMessageText(newMessageText);
	}

	public void deleteSingleMessage(MessageModel message) {
		userMessages.remove(message);
	}

	public void deleteSingleMessageById(int id) {
		userMessages.remove(id);
	}

	public void deleteSingleMessageByText(String messageText) {
		userMessages.remove(getSingleMessageByText(messageText));
	}

	public void deleteAllMessages() {
		userMessages.clear();
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
