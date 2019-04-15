package de.fhws.fiw.pvs.assignment_2.ex02;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FactorialServiceImpl extends UnicastRemoteObject implements FactorialService {

    public FactorialServiceImpl( ) throws RemoteException
    {
        super( );
    }

    @Override
    public long factorial(long a) throws RemoteException {
        if(a < 2) return 1;
        else return a * factorial(a-1);
    }

}
