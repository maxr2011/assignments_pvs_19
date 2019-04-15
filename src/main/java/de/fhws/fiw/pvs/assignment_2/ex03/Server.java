package de.fhws.fiw.pvs.assignment_2.ex03;

import java.rmi.Naming;

public class Server {

    public static void main(String[] args) {

        try {

            final ObjectService objectService = new ObjectServiceImpl();
            Naming.rebind( objectService.SERVICE_NAME, objectService );

            System.out.println("Server is running ...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

