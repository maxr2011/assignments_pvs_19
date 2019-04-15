package de.fhws.fiw.pvs.assignment_2.grpc;

import io.grpc.stub.StreamObserver;

/**
 * Created by braunpet on 18.04.17.
 */
public class GreeterServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase
{

	@Override public void getGreeting( final Greeter.Request request,
		final StreamObserver<Greeter.Reply> responseObserver )
	{
		final String name = request.getName( );
		final String greeting = "Hallo " + name + "!";
		final Greeter.Reply reply = Greeter.Reply.newBuilder( ).setGreeting( greeting ).build( );
		responseObserver.onNext( reply );
		responseObserver.onCompleted( );
	}
}
