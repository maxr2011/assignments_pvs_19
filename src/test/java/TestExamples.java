import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import de.fhws.fiw.pvs.assignment_5.example.models.PersonModel;
import de.fhws.fiw.pvs.assignment_5.sutton.client.Link;
import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestExamples
{
	private final static String BASE_URL = "http://localhost:8080/example/api";
	private final static String CREATE_PERSON = "createPerson";
	private final static String GET_ALL_PERSONS = "getAllPersons";
	private final static String UPDATE_SINGLE_PERSON = "updatePerson";
	private final static String DELETE_SINGLE_PERSON = "deletePerson";
	private final static String GET_SINGLE_PERSON = "getPerson";

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
		final Optional<Link> createPersonLink = callDispatcherAndGetHeaderLinkWithRelType( CREATE_PERSON );

		final Link theCreateLink = createPersonLink.get( );
		final PersonModel person = new PersonModel( );
		person.setFirstName( "Peter" );
		person.setLastName( "Braun" );
		final RequestBody body = RequestBody.create(
			MediaType.parse( theCreateLink.getMediaType( ) ),
			this.genson.serialize( person ) );
		final Request requestPost = new Request.Builder( ).url( theCreateLink.getUrl( ) ).post( body ).build( );
		final Response responsePost = executeRequest( requestPost );
		assertTrue( "Object was not created!", responsePost.code( ) == 201 );
	}

	@Test
	public void testGetCollection( )
	{
		final Optional<Link> getAllPersons = callDispatcherAndGetHeaderLinkWithRelType( GET_ALL_PERSONS );

		final Link theGetAllLink = getAllPersons.get( );
		final Request requestGetAll = new Request.Builder( ).url( theGetAllLink.getUrl( ) )
															.get( )
															.build( );

		final Response responseGetAll = executeRequest( requestGetAll );

		assertTrue( "Get request failed!", responseGetAll.code( ) == 200 );
	}

	@Test
	public void testPut( ) throws Exception
	{
		final Optional<Link> createPersonLink = callDispatcherAndGetHeaderLinkWithRelType( CREATE_PERSON );

		/* Create new person */
		final Link theCreateLink = createPersonLink.get( );
		final PersonModel person = new PersonModel( );
		person.setFirstName( "Peter" );
		person.setLastName( "Braun" );
		final RequestBody body = RequestBody.create(
			MediaType.parse( theCreateLink.getMediaType( ) ),
			this.genson.serialize( person ) );
		final Request requestPost = new Request.Builder( ).url( theCreateLink.getUrl( ) ).post( body ).build( );
		final Response responsePost = executeRequest( requestPost );
		assertTrue( "Object was not created!", responsePost.code( ) == 201 );

		/* Get single person that was just created */
		final String locationHeader = responsePost.header( "Location" );
		final Request requestGetSingle = new Request.Builder( ).url( locationHeader ).get( ).build( );
		final Response responseGetSingle = executeRequest( requestGetSingle );

		final PersonModel personResponse =
			this.genson.deserialize( responseGetSingle.body( ).string( ), PersonModel.class );
		assertEquals( "Peter", personResponse.getFirstName( ) );

		/* Update this person */
		final Optional<Link> linkToUpdate = getResponseHeaderLink( responseGetSingle, UPDATE_SINGLE_PERSON );
		assertTrue( String.format( "No link of relType '%s' found.", UPDATE_SINGLE_PERSON ),
			linkToUpdate.isPresent( ) );

		personResponse.setFirstName( "Julius" );

		final RequestBody putBody = RequestBody.create(
			MediaType.parse( linkToUpdate.get( ).getMediaType( ) ),
			this.genson.serialize( personResponse ) );
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
