package de.fhws.fiw.pvs.assignment_1.ex02;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * Created by braunpet on 04.04.17.
 */
public class TcpClient
{
	public static void main( final String[] argv ) throws Exception
	{
		byte[] bytearr = {1, 0, 1, 0};
		sendMessage(bytearr);
	}

	static byte[] sendMessage( final byte[] message ) throws Exception {
		final Socket clientSocket = new Socket( "localhost", 6789 );

		final OutputStream outputStream = clientSocket.getOutputStream();

		outputStream.write(message);
		System.out.println("Message sent to server!");


		outputStream.write(-1);
		outputStream.flush();
		//outputStream.close();
		//clientSocket.close();

		//final Socket newClientSocket = new Socket("localhost", 6789);

		final InputStream inputStream = clientSocket.getInputStream();

		System.out.print("Message received: ");
		byte[] received = IOUtils.toByteArray(inputStream);

		for(byte b : received) {
			System.out.print(b);
		}

		inputStream.close();
		clientSocket.close();

		return received;
	}



}
