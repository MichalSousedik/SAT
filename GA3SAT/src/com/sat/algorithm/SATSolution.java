package com.sat.algorithm;

import com.sat.Formula;

public class SATSolution {

        private Formula formula;
        private int maxValue;
        private int[]bestCombination;

    SATSolution(Formula formula, int maxValue, int[] bestCombination) {
        this.formula = formula;
        this.maxValue = maxValue;
        this.bestCombination = bestCombination;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(formula.getId() + " " + formula.getVariablesCount() + " " + maxValue + "  ");
        if(maxValue == 0)
            return "0\n";
//            return "Solution was not found (does not exist or algorithm did not find it)";
        else {
//            for (int i = 0; i < bestCombination.length; i++) {
//                s.append(bestCombination[i]);
//                if (i != bestCombination.length - 1)
//                    s.append(" ");
//                else
//                    s.append("\n");
//            }
            return maxValue + "\n";

        }
//        return s.toString();
    }
}
