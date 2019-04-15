package de.fhws.fiw.pvs.assignment_2.ex03;

import de.fhws.fiw.pvs.assignment_2.ex03.objects.B;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObjectService extends Remote {

    String SERVICE_NAME = "ObjectService";

    void f(B a) throws RemoteException;

}
