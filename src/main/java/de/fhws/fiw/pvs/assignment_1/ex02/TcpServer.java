package de.fhws.fiw.pvs.assignment_1.ex02;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by braunpet on 04.04.17.
 */

// Exercise 2
public class TcpServer
{
	public static void main( final String[] args ) throws Exception
	{
		final ServerSocket serverSocket = new ServerSocket( 6789 );
		System.out.println("TCP Server started.");
		while ( true )
		{
			final Socket socket = serverSocket.accept( );
			System.out.println("Socket accepted.");

			final InputStream inputStream = socket.getInputStream();
			System.out.println("InputStream and OutputStream initialised.");

			byte[] received = IOUtils.toByteArray(socket.getInputStream());

			System.out.print("Received from Client: ");

			for(byte b : received) {
				System.out.print(b);
			}

			System.out.println();

			final OutputStream outputStream = socket.getOutputStream();
			outputStream.write(received);

			outputStream.flush( );
			outputStream.close( );

			socket.close( );

		}
	}

}
