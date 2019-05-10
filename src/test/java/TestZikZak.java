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

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import de.fhws.fiw.pvs.assignment_5.sutton.client.Link;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.MessageModel;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.UserModel;
import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestZikZak
{
	// user
	private final static String BASE_URL = "http://localhost:8080/zikzak/api";
	private final static String CREATE_USER = "createUser";
	private final static String GET_ALL_USERS = "getAllUsers";
	private final static String UPDATE_SINGLE_USER = "updateUser";
	private final static String DELETE_SINGLE_USER = "deleteUser";
	private final static String GET_SINGLE_USER = "getUser";

	// message
	private final static String CREATE_MESSAGE = "createMessage";
	private final static String GET_ALL_MESSAGES = "getAllMessages";
	private final static String GET_SINGLE_MESSAGE = "getMessage";

	private OkHttpClient client;

	private Genson genson;

	@Before
	public void setUp( )
	{
		this.client = new OkHttpClient( );
		this.genson = new GensonBuilder( ).setSkipNull( true )
				.useIndentation( true )
				.useDateAsTimestamp( false )
				.useDateFormat( new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" ) )
				.create( );
	}

	@After
	public void tearDown( )
	{

	}

	@Test
	public void testPost( )
	{
		final Optional<Link> createUserLink = callDispatcherAndGetHeaderLinkWithRelType( CREATE_USER );

		final Link theCreateLink = createUserLink.get( );
		final UserModel user = new UserModel( );
		user.setFirstName( "Peter" );
		user.setLastName( "Braun" );
		final RequestBody body = RequestBody.create(
				MediaType.parse( theCreateLink.getMediaType( ) ),
				this.genson.serialize( user ) );
		final Request requestPost = new Request.Builder( ).url( theCreateLink.getUrl( ) ).post( body ).build( );
		final Response responsePost = executeRequest( requestPost );
		assertTrue( "Object was not created!", responsePost.code( ) == 201 );
	}

	@Test
	public void testPostMessageForUser( )
	{
		final Optional<Link> createMessageLink = callDispatcherAndGetHeaderLinkWithRelType( CREATE_MESSAGE );

		final Link theCreateLink = createMessageLink.get( );
		final MessageModel message = new MessageModel( );
		message.setMessageText("Hello World!");
		final RequestBody body = RequestBody.create(
				MediaType.parse( theCreateLink.getMediaType( ) ),
				this.genson.serialize( message )
		);
		final Request requestPost = new Request.Builder( ).url( theCreateLink.getUrl( ) ).post( body ).build( );
		final Response responsePost = executeRequest( requestPost );
		assertTrue("Object was not created!", responsePost.code( ) == 201);
	}

	@Test
	public void testGetCollection( )
	{
		final Optional<Link> getAllUsers = callDispatcherAndGetHeaderLinkWithRelType( GET_ALL_USERS );

		final Link theGetAllLink = getAllUsers.get( );
		final Request requestGetAll = new Request.Builder( ).url( theGetAllLink.getUrl( ) )
				.get( )
				.build( );

		final Response responseGetAll = executeRequest( requestGetAll );

		assertTrue( "Get request failed!", responseGetAll.code( ) == 200 );
	}

	@Test
	public void testGetMessages( )
	{
		final Optional<Link> getAllMessages = callDispatcherAndGetHeaderLinkWithRelType( GET_ALL_MESSAGES );

		final Link theGetAllLink = getAllMessages.get( );
		final Request requestGetAll = new Request.Builder( ).url( theGetAllLink.getUrl( ) )
				.get( )
				.build( );

		final Response responseGetAll = executeRequest( requestGetAll );
	}

	@Test
	public void testPut( ) throws Exception
	{
		final Optional<Link> createUserLink = callDispatcherAndGetHeaderLinkWithRelType( CREATE_USER );

		/* Create new user */
		final Link theCreateLink = createUserLink.get( );
		final UserModel user = new UserModel( );
		user.setFirstName( "Peter" );
		user.setLastName( "Braun" );
		final RequestBody body = RequestBody.create(
				MediaType.parse( theCreateLink.getMediaType( ) ),
				this.genson.serialize( user ) );
		final Request requestPost = new Request.Builder( ).url( theCreateLink.getUrl( ) ).post( body ).build( );
		final Response responsePost = executeRequest( requestPost );
		assertTrue( "Object was not created!", responsePost.code( ) == 201 );

		/* Get single user that was just created */
		final String locationHeader = responsePost.header( "Location" );
		final Request requestGetSingle = new Request.Builder( ).url( locationHeader ).get( ).build( );
		final Response responseGetSingle = executeRequest( requestGetSingle );

		final UserModel userResponse =
				this.genson.deserialize( responseGetSingle.body( ).string( ), UserModel.class );
		assertEquals( "Peter", userResponse.getFirstName( ) );

		/* Update this user */
		final Optional<Link> linkToUpdate = getResponseHeaderLink( responseGetSingle, UPDATE_SINGLE_USER );
		assertTrue( String.format( "No link of relType '%s' found.", UPDATE_SINGLE_USER ),
				linkToUpdate.isPresent( ) );

		userResponse.setFirstName( "Julius" );

		final RequestBody putBody = RequestBody.create(
				MediaType.parse( linkToUpdate.get( ).getMediaType( ) ),
				this.genson.serialize( userResponse ) );
		final Request requestPut = new Request.Builder( ).url( linkToUpdate.get( ).getUrl( ) ).put( putBody ).build( );
		final Response responsePut = executeRequest( requestPut );
		assertTrue( "Object was not updated!", responsePut.code( ) == 204 );

	}

	protected Optional<Link> callDispatcherAndGetHeaderLinkWithRelType( final String relType )
	{
		final Request requestDispatcher = new Request.Builder( ).url( BASE_URL ).get( ).build( );
		final Response responseDispatcher = executeRequest( requestDispatcher );
		final Optional<Link> link = getResponseHeaderLink( responseDispatcher, relType );
		assertTrue( String.format( "No link of relType '%s' found.", relType ), link.isPresent( ) );
		return link;
	}

	protected Response executeRequest( final Request request )
	{
		Response response;

		try
		{
			response = this.client.newCall( request ).execute( );
		}
		catch ( final IOException e )
		{
			response = null;
		}

		return response;
	}

	protected Optional<Link> getResponseHeaderLink( final Response response, final String relType )
	{
		return response.headers( "Link" )
				.stream( )
				.map( v -> Link.parseFromHttpHeader( v ) )
				.filter( l -> l.getRelationType( ).equalsIgnoreCase( relType ) )
				.findFirst( );
	}
}
