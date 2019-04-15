package de.fhws.fiw.pvs.assignment_2.ex02;

import java.rmi.Naming;

public class Client {

    private static final String ADDRESS_FACTORIAL = "rmi://localhost/" + FactorialService.SERVICE_NAME;

    public static void main(String[] args) {

        try {

            final FactorialService factorialService = ( FactorialService ) Naming.lookup( ADDRESS_FACTORIAL );

            long x = System.nanoTime();
            for (int i = 0; i < 66; i++) {
                long a = System.nanoTime();
                System.out.println(i + ". " + factorialService.factorial(i));
                long b = System.nanoTime();
                long c = b - a;
                System.out.println("Zeit gedauert: " + c);
            }
            long y = System.nanoTime();
            long z = y - x;
            System.out.println("Insgesammt Zeit verbraucht: " + z);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
