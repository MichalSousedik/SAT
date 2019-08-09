package com.sat.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Member {

    private List<Boolean> gens;

    private int fitness;

    Member(List<Boolean> gens) {
        this.gens = gens;
    }

    List<Boolean> getGens() {
        return gens;
    }

    int getFitness() {
        return fitness;
    }

    void setFitness(int fitness) {
        this.fitness = fitness;
    }

    static Member generateRandomMember(int memberGenSize) {
        List<Boolean> gens = new ArrayList<>();
        for(int genIndex = 0; genIndex < memberGenSize; genIndex++){
            // 50 % chance of getting true
            gens.add(new Random().nextInt() % 2 == 0);
        }
        return new Member(gens);
    }

    void mutate(double mutationRate) {
        for(int i = 0; i < gens.size(); i++)
            if (new Random().nextDouble() <= mutationRate) {
                Boolean newGen = !gens.get(i);
                gens.set(i, newGen);
            }
    }

    int[] getGensAsArray() {
        int[] solution = new int[gens.size()];
        for (int i = 0; i < gens.size(); i++)
            if (gens.get(i))
                solution[i] = 1;
            return solution;
    }
}
