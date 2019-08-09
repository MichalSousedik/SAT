package com.company;

import java.util.*;

public class SATGenerator {

    public static void main(String[] args) {
        if (args.length != 5) return;
        int clauses = Integer.parseInt(args[0]);
        int terms = Integer.parseInt(args[1]);
        int maxWeight = Integer.parseInt(args[2]);
        int maxTerms = Integer.parseInt(args[3]);
        double negativeProbability = Double.parseDouble(args[4]);

//        int clauses = 3;
//        int terms = 10;

        System.out.println("c SAT instance in DIMACS CNF input format.");
        System.out.println("p cnf " + terms + " " + clauses);


        for (int clausuleIndex = 0; clausuleIndex < clauses; clausuleIndex++) {
            List<Variable> variables = new ArrayList<>();
            for (int l = 0; l < maxTerms; l++) {
                Variable variable;
                do {
                    variable = new Variable(new Random().nextInt(terms) + 1, !(new Random().nextDouble() <= negativeProbability));
                } while (variables.contains(variable));
                variables.add(variable);
            }

            variables.sort(Comparator.comparingInt(Variable::getIndex));
            variables.forEach(System.out::print);
            System.out.println("0");
        }

        for (int i = 0; i < terms; i++) {
            System.out.print(new Random().nextInt(maxWeight) + " ");
        }

    }

}
