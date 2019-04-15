package de.fhws.fiw.pvs.assignment_2.ex01;

import java.io.Serializable;
import java.util.List;

public class Branch implements Serializable {

    private static final long serialVersionUID = 3900429985533025240L;
    List<Leaf> leafs;

    List<Branch> branches;

    public Branch(List leafs , List branches){
        this.leafs=leafs;
        this.branches=branches;
    }

}
