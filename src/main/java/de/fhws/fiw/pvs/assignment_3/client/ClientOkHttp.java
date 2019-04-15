package de.fhws.fiw.pvs.assignment_3.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ClientOkHttp {

    public static void main( String[] args )
    {
        final OkHttpClient client = new OkHttpClient( );

        Request request = new Request.Builder( )
                .url( "http://localhost:8080/person/personTest" )
                .get()
                .build( );

        try
        {
            Response response = client.newCall( request ).execute( );
            System.out.println( response.body().string() );
        }
        catch ( IOException e )
        {
            e.printStackTrace( );
        }
    }

}
