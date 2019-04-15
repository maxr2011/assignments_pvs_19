package de.fhws.fiw.pvs.assignment_2.ex03;

import de.fhws.fiw.pvs.assignment_2.ex03.objects.A;
import de.fhws.fiw.pvs.assignment_2.ex03.objects.B;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjectServiceImpl extends UnicastRemoteObject implements ObjectService {

    public ObjectServiceImpl( ) throws RemoteException
    {
        super( );
    }

    @Override
    public void f(B b) throws RemoteException {
        A a = new A();
        a.f(b);
    }

}
