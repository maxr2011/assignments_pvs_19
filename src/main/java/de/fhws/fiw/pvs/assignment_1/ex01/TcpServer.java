package de.fhws.fiw.pvs.assignment_1.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by braunpet on 04.04.17.
 */

// Exercise 1 Multi-Threaded Socket Server
public class TcpServer
{
    public static long globalMillis;

	public static void main( final String[] args ) throws Exception
	{
	    globalMillis = gettime();
		final ServerSocket serverSocket = new ServerSocket( 6789 );
		System.out.println("TCP Server started.");
		while ( true )
		{
			final Socket socket = serverSocket.accept( );
			System.out.println("Awaiting new Input");
			Thread thread = new Thread( ()  -> {
				try {
					getAndReadInput(socket);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			thread.run();
		}
	}

	public static void getAndReadInput(Socket socket) throws Exception {
		final InputStreamReader inputStreamReader = new InputStreamReader( socket.getInputStream( ) );
		final BufferedReader inFromClient = new BufferedReader( inputStreamReader );
		final String input = inFromClient.readLine( );

		System.out.println( "Received from Client: " + input );
		long actualMillis = gettime() - globalMillis;
        System.out.println(actualMillis);

		final String output = input.toUpperCase( );

		final OutputStreamWriter outputStreamWriter = new OutputStreamWriter( socket.getOutputStream( ) );
		final BufferedWriter bufferedWriter = new BufferedWriter( outputStreamWriter );
		bufferedWriter.append( output ).append( '\n' );
		bufferedWriter.flush( );
		bufferedWriter.close( );
		inFromClient.close( );
		socket.close( );
	}

    public static long gettime() //Methode für aktuelle Zeit in Millisekunden
    {
        return System.currentTimeMillis();
    }
}
