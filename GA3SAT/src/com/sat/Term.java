package com.sat;


import java.util.List;

class Term {
    private Variable variable;
    private Boolean isPositive;

    Term(Variable variable, Boolean isPositive) {
        this.variable = variable;
        this.isPositive = isPositive;
    }

    boolean isPositive(List<Boolean> gens) {
        if(gens.get(variable.getIndex()-1))
            return isPositive;
        else
            return !isPositive;
    }
}
