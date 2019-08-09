package com.sat.algorithm;

import com.sat.Formula;
import com.sat.factory.SATSolver;
import com.sat.helpers.Constant;

import java.util.*;

public class SATSolverGA implements SATSolver {

    private double populationSizePercentage;
    private int generationsNumber;
    private int tournamentSize;
    private double crossoverProbability;
    private double mutationRate;
    private int elitism;
    private Population population;

    public SATSolverGA(Map<String, String> parameters) {
        this.populationSizePercentage = Double.parseDouble(parameters.get(Constant.POPULATION));
        this.generationsNumber = Integer.parseInt(parameters.get(Constant.GENERATIONS));
        this.tournamentSize = Integer.parseInt(parameters.get(Constant.TOURNAMENT_SIZE));
        this.crossoverProbability = Double.parseDouble(parameters.get(Constant.CROSSOVER_PROBABILITY));
        this.mutationRate = Double.parseDouble(parameters.get(Constant.MUTATION_RATE));
        this.elitism = Integer.parseInt(parameters.get(Constant.ELITISM));
    }

    @Override
    public SATSolution solve(Formula formula) {
        this.population = Population.generateInitialPopulation(formula, (int) this.populationSizePercentage * formula.getVariablesCount());
        return solveInstance(formula);
    }

    private SATSolution solveInstance(Formula formula) {
//            1. selekce
//            2. krizeni
//            3. mutace
        for (int generationIndex = 0; generationIndex < this.generationsNumber; generationIndex++) {
            List<Member> nextGeneration = new ArrayList<>();
            List<Member> children = new ArrayList<>();
            List<Member> selection = tournamentSelection(nextGeneration);
            List<Member> potentialParents = decideOnParenthood(selection);
            if (potentialParents.size() >= 2)
                children.addAll(uniformCrossover(potentialParents, selection, formula));
            children.addAll(selection);
            nextGeneration.addAll(binaryMutation(children, formula));
            population.moveToNextGeneration(nextGeneration);
            population.getMembers().forEach(member -> {
                System.out.println(member.getFitness() + " " + Arrays.toString(member.getGensAsArray()));
            });
        }

        Member bestMember = population.getFittestMember();
        return new SATSolution(formula, bestMember.getFitness(), bestMember.getGensAsArray());
    }

    private List<Member> decideOnParenthood(List<Member> selection) {
        List<Member> potentialParents = new ArrayList<>();
        for (Member member : selection) {
            if (new Random().nextDouble() <= crossoverProbability) {
                potentialParents.add(member);
            }
        }
        for (Member potentialParent : potentialParents)
            selection.remove(potentialParent);
        return potentialParents;
    }

    private List<Member> tournamentSelection(List<Member> nextGeneration) {
        List<Member> selection = new ArrayList<>();
        population.getMembers().sort(Comparator.comparingInt(Member::getFitness).reversed());
        for (int i = 0; i < Math.min(elitism, population.getPopulationSize()); i++) {
            nextGeneration.add(population.getMember(i));
            population.removeMember(i);
        }
        for (int k = 0; k < population.getPopulationSize(); k++) {
            List<Member> tournamentPool = new ArrayList<>();
            for (int i = 0; i < Integer.min(tournamentSize, population.getPopulationSize()); i++) {
                tournamentPool.add(population.getRandomMember());
            }
            if (tournamentPool.size() > 0)
                selection.add(tournamentPool.stream().max(Comparator.comparing(Member::getFitness)).get());
        }
        return selection;
    }

    private List<Member> binaryMutation(List<Member> children, Formula formula) {
        children.forEach(member -> {
            member.mutate(mutationRate);
            member.setFitness(Population.countMemberFitness(member, formula));
        });
        return children;
    }


    private List<Member> uniformCrossover(List<Member> potentialParents, List<Member> selection, Formula formula) {
        List<Boolean> uniformList = createUniformList(population.getMemberGenSize());
        List<Member> children = new ArrayList<>();
        Random random = new Random();
        for (Member parent1 : potentialParents) {
            Member parent2 = potentialParents.get(random.nextInt(potentialParents.size()));
            if (parent1 != parent2) {
                children.add(createChild(parent1, parent2, uniformList, formula));
            } else {
                selection.add(parent1);
            }
        }
        return children;
    }

    private Member createChild(Member parent1, Member parent2, List<Boolean> uniformList, Formula formula) {
        List<Boolean> childGens = new ArrayList<>();
        for (int i = 0; i < uniformList.size(); i++) {
            if (uniformList.get(i)) {
                childGens.add(parent1.getGens().get(i));
            } else {
                childGens.add(parent2.getGens().get(i));
            }
        }
        Member child = new Member(childGens);
        child.setFitness(Population.countMemberFitness(child, formula));
        return child;
    }

    private List<Boolean> createUniformList(int populationSize) {
        List<Boolean> uniformList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < populationSize; i++)
            uniformList.add(random.nextBoolean());
        return uniformList;
    }
}
