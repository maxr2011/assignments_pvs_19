package de.fhws.fiw.pvs.assignment_2.ex03;

import de.fhws.fiw.pvs.assignment_2.ex03.objects.B;

import java.rmi.Naming;

public class Client {

    private static final String ADDRESS_OBJECTS = "rmi://localhost/" + ObjectService.SERVICE_NAME;

    public static void main(String[] args) {

        try {

            // Service
            final ObjectService objectService = ( ObjectService ) Naming.lookup( ADDRESS_OBJECTS );

            // Creating Object
            B b = new B("X", "Y");

            // Invoking Object
            objectService.f(b);

            System.out.println("Client is running ...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
