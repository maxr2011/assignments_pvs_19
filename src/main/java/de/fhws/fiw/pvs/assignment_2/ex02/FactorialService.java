package de.fhws.fiw.pvs.assignment_2.ex02;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FactorialService extends Remote {

    String SERVICE_NAME = "FactorialService";

    long factorial(long a) throws RemoteException;

}
