package de.fhws.fiw.pvs.assignment_2.ex02;

public class Main {

    public static void main(String[] args) {
        long x = System.nanoTime();
        for (int i = 0; i < 65; i++) {
            long a = System.nanoTime();
            System.out.println(i + ". " + Math.factorial(i));
            long b = System.nanoTime();
            long c = b - a;
            System.out.println("Zeit gedauert: " + c);
        }
        long y = System.nanoTime();
        long z = y - x;
        System.out.println("Insgesammt Zeit verbraucht: " + z);
    }

}
