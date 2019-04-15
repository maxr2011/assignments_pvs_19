package de.fhws.fiw.pvs.assignment_2.ex02;

import java.rmi.Naming;

public class Server {

    public static void main(String[] args) {

        try {

            final FactorialService factorialService = new FactorialServiceImpl( );
            Naming.rebind( factorialService.SERVICE_NAME, factorialService );

            System.out.println("Server is running ...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

