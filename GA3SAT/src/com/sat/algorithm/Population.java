package com.sat.algorithm;

import com.sat.Clause;
import com.sat.Formula;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class Population {

    private List<Member> members;
    private int memberGenSize;

    private Population(int memberGenSize) {
        this.members = new ArrayList<>();
        this.memberGenSize = memberGenSize;
    }


    static Population generateInitialPopulation(Formula formula, int populationSize) {
        int memberGenSize = formula.getVariablesCount();
        Population population = new Population(memberGenSize);
        for (int memberIndex = 0; memberIndex < populationSize; memberIndex++) {
            Member member = Member.generateRandomMember(memberGenSize);
            member.setFitness(countMemberFitness(member, formula));
            population.addMember(member);
        }
        return population;
    }

    private void addMember(Member m) {
        members.add(m);
    }

    int getPopulationSize() {
        return members.size();
    }

    Member getMember(int index) {
        if (index < members.size())
            return members.get(index);
        return null;
    }

    void removeMember(int index) {
        if (index < members.size())
            this.members.remove(index);
    }

    Member getRandomMember() {
        Random random = new Random();
        return members.get(random.nextInt(members.size()));
    }

    void moveToNextGeneration(List<Member> members) {
        this.members = members;
    }

    static Integer countMemberFitness(Member member, Formula formula) {

        for (Clause clause : formula.getClauses()) {
            if (!clause.isPositive(member.getGens()))
                return 0;
        }

        int index = 1;
        int fitness = 0;
        for (Boolean gen : member.getGens()) {
            if (gen) {
                fitness += formula.getVariable(index).getWeight();
            }
            index++;
        }
        return fitness;

    }

    List<Member> getMembers() {
        return members;
    }

    int getMemberGenSize() {
        return memberGenSize;
    }

    Member getFittestMember() {
        members.sort(Comparator.comparingInt(Member::getFitness).reversed());
        if (getPopulationSize() > 0)
            return members.get(0);
        return null;
    }
}
