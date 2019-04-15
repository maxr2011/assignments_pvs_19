package de.fhws.fiw.pvs.assignment_3.client;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientUrlConnection {

    public static void main( String[] args )
    {
        try
        {
            final URL uri = new URL( "http://localhost:8080/person/personTest" );
            final HttpURLConnection con = ( HttpURLConnection ) uri.openConnection( );

            con.setDoInput( true );
            con.setRequestMethod( "GET" );

            final InputStream inputStream = con.getInputStream( );
            String responseData = IOUtils.toString( inputStream );

            System.out.println(  responseData );
        }
        catch ( final Exception e )
        {
            e.printStackTrace( );
        }
    }

}
