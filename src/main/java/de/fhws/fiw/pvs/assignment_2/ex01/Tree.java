package de.fhws.fiw.pvs.assignment_2.ex01;
import java.io.Serializable;
import java.util.List;

public class Tree implements Serializable {
      List <Branch> branchList;

      public Tree(List branchList){
          this.branchList =branchList;
    }

}
