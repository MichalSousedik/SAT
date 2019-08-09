package com.sat;

public class Variable {

    private int index;
    private int weight;

    Variable(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    int getIndex() {
        return index;
    }
}
