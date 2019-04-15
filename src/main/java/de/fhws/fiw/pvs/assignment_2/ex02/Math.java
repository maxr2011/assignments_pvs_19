package de.fhws.fiw.pvs.assignment_2.ex02;

public class Math {

    public static long factorial(long a){
        if(a < 2) return 1;
        else return a * factorial(a-1);
    }

}
