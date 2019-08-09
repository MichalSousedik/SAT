package com.sat;

import java.util.List;

public class Clause {

    private List<Term> terms;

    Clause(List<Term> terms) {
        this.terms = terms;
    }
    public boolean isPositive(List<Boolean> gens) {
        for(Term term : this.terms)
            if(term.isPositive(gens))
                return true;
        return false;
    }
}
