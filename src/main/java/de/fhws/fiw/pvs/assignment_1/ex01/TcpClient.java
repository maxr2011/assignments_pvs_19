package de.fhws.fiw.pvs.assignment_1.ex01;

import java.io.*;
import java.net.Socket;

/**
 * Created by braunpet on 04.04.17.
 */
public class TcpClient
{
	public static void main( final String[] argv ) throws Exception
	{
		Thread a = new Thread(() -> {
			try {
				for(int i = 0; i < 1000; i++) {
					sendMessage(Integer.toString(i) + "Hallo ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Thread b = new Thread(() -> {
			try {
				for(int i = 0; i < 1000; i++) {
					sendMessage(Integer.toString(i) + "Welt! ");
				}
			} catch (Exception f)  {
				f.printStackTrace();
			}
		});
		a.start();
		b.start();
		a.join();
		b.join();
	}

	private static String sendMessage(String msg) throws Exception {
		final Socket clientSocket = new Socket( "localhost", 6789 );

		final OutputStreamWriter outToServer = new OutputStreamWriter( clientSocket.getOutputStream( ) );
		final InputStreamReader inputStreamReader = new InputStreamReader( clientSocket.getInputStream( ) );
		final BufferedReader inFromServer = new BufferedReader( inputStreamReader );

		//System.out.println( "INPUT: " );
		//final BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		//final String input = inFromUser.readLine( );

		final String input = msg;

		outToServer.append( input ).append( '\n' );
		outToServer.flush( );

		final String output = inFromServer.readLine( );
		clientSocket.close( );

		return output;
	}

}
