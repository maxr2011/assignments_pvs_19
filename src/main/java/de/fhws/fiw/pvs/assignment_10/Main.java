package de.fhws.fiw.pvs.assignment_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {

        // initialise some values
        int N = 4;
        Integer[] A = {5, 7, 8, 1};

        // measure time here
        Integer[] B = sequentialPrefix(A);
        Integer[] P = parallelPrefix(A);

        // print output sequential
        System.out.println("Sequential: ");
        printOutArray(B);

        System.out.println();

        // print output parallel
        System.out.println("Parallel: ");
        printOutArray(P);

    }

    private static Integer[] sequentialPrefix(Integer[] input) {

        Integer[] output = new Integer[input.length];

        for(int i = 0; i < input.length; i++) {
            output[i] = input[i];
            for(int j = 0; j < i; j++) {
                output[i] += input[j];
            }
        }

        return output;

    }

    private static Integer[] parallelPrefix(Integer[] input) {
        // todo implement
        Integer[] output = new Integer[input.length];

        for(Integer it : input) {
            new Thread(() -> {
                // todo implement parallel
            }).start();
        }

        return output;
    }

    private static void printOutArray(Integer[] arrayPrint) {

        System.out.print("arrayPrint: {");
        for(Integer i : arrayPrint) {
            System.out.print(i + ", ");
        }
        System.out.print("}");

    }

}
