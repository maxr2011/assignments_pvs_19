package de.fhws.fiw.pvs.assignment_2.ex01;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        List<Branch> br1 = new ArrayList<>();
        Leaf l1 = new Leaf();
        Leaf l2 = new Leaf();
        Leaf l3 = new Leaf();
        List<Leaf> leafList = new ArrayList<>();
        leafList.add(l1);
        leafList.add(l2);
        Branch b1 = new Branch(leafList, null);
        leafList.clear();
        leafList.add(l3);
        List<Branch> branchList = new ArrayList<>();
        branchList.add(b1);
        Branch b2 = new Branch(leafList, branchList);

        branchList.clear();
        branchList.add(b2);
        Tree TestTree= new Tree(branchList);

        String filename = "C:/Users/maxr2/IdeaProjects/distributed-java-2019ss/src/main/java/de/fhws/fiw/pvs/week03/assignment_2/ex01/file.ser";

        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(TestTree);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

    }
}
