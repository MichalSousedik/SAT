package com.company;


import java.util.Objects;

public class Variable {

   private int index = 0;
   private Boolean isPositive;

    public Variable(int index, Boolean isPositive) {
        this.index = index;
        this.isPositive = isPositive;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Boolean getPositive() {
        return isPositive;
    }

    public void setPositive(Boolean positive) {
        isPositive = positive;
    }


    @Override
    public String toString() {
        return index * (isPositive ? 1 : -1) + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return index == variable.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, isPositive);
    }
}
