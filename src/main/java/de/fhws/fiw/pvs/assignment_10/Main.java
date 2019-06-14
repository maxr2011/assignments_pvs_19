package de.fhws.fiw.pvs.assignment_10;

public class Main {

    public static void main(String[] args) {

        // initialise some values
        int N = 4;
        Integer[] A = {5, 7, 8, 1};
        Integer[] B = sequentialPrefix(A);

        // print output
        printOutArray(B);

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

    private static void printOutArray(Integer[] arrayPrint) {

        System.out.print("arrayPrint: {");
        for(Integer i : arrayPrint) {
            System.out.print(i + ", ");
        }
        System.out.print("}");

    }

}
