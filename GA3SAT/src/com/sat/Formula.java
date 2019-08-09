package com.sat;

import java.util.List;
import java.util.Map;

public class Formula {

    private int id;
    private List<Clause> clauses;
    private Map<Integer, Variable> variables;

    Formula(Integer id, List<Clause> clauses, Map<Integer, Variable> variables) {
        this.id = id;
        this.clauses = clauses;
        this.variables = variables;
    }

    public Variable getVariable(int index){
        return variables.get(index);
    }

    public Integer getVariablesCount(){
        return this.variables.size();
    }

    public int getId() {
        return id;
    }

    public List<Clause> getClauses() {
        return clauses;
    }
}
