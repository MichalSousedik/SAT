package com.sat;

import com.sat.factory.SATSolverFactory;
import com.sat.helpers.CPUTimer;
import com.sat.helpers.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class SATProblemSolver {

//        args[0] -- fileName
//        args[2] -- count time
//        args[3] -- number of iteration for counting time
    public static void main(String[] args) throws IOException {
//        if (args.length > 3) {
//            String fileName = args[0];
//            String algorithm = args[1];
//            Boolean countTime = Boolean.parseBoolean(args[2]);
//            int numberOfIterations = Integer.parseInt(args[3]);

            String fileName = "uf20-01.cnf";
            String algorithm = "ga";
            Boolean countTime = false;
            int numberOfIterations = 1;

            if (fileName != null) {
                List<Formula> formulas = readFile(fileName);
                if (countTime) {
                    CPUTimer cpuTimer = new CPUTimer();
                    for (int i = 0; i < numberOfIterations; i++) {
                        cpuTimer.start();
                        startAlgorithm(formulas, algorithm, countTime, createParameters(args, algorithm));
                        cpuTimer.stop();
                    }
                    cpuTimer.writeAverageTime();
                }
                else
                    startAlgorithm(formulas, algorithm, countTime, createParameters(args, algorithm));
            }
//        }
    }

    private static Map<String, String> createParameters(String[] args, String algorithm){
        Map<String, String> parameters = new HashMap<>();
//        if(args.length > 9) {
//            parameters.put(Constant.GENERATIONS, args[4]);
//            parameters.put(Constant.POPULATION, args[5]);
//            parameters.put(Constant.TOURNAMENT_SIZE, args[6]);
//            parameters.put(Constant.CROSSOVER_PROBABILITY, args[7]);
//            parameters.put(Constant.MUTATION_RATE, args[8]);
//            parameters.put(Constant.ELITISM, args[9]);
//        }
        parameters.put(Constant.GENERATIONS, "100000");
        parameters.put(Constant.POPULATION, "10");
        parameters.put(Constant.TOURNAMENT_SIZE, "10");
        parameters.put(Constant.CROSSOVER_PROBABILITY, "0.8");
        parameters.put(Constant.MUTATION_RATE, "0.8");
        parameters.put(Constant.ELITISM, "2");

        return parameters;
    }

    private static void startAlgorithm(List<Formula> formulas, String algorithm, Boolean countTime, Map<String, String> parameters){
        for (Formula formula : formulas) {
            printSolution(SATSolverFactory.getSolver(algorithm, parameters).solve(formula).toString(), countTime);
        }
    }


    private static void printSolution(String solution, Boolean countTime) {
//        if (!countTime)
            System.out.print(solution);
    }

    private static List<Formula> readFile(String fileName) {
        List<Formula> formulas = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            Map<Integer, Variable> variables = new HashMap<>();
            int clausesCount = 0;
            int formulaId = 0;
                    List<Clause> clauseList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.startsWith("p")){
                    String[] blocks = line.split(" ");
                    for(int i = 1; i <= Integer.parseInt(blocks[2])  ; i++)
                        variables.put(i, new Variable(i));
                    clausesCount = Integer.parseInt(blocks[3]);
                }
                else if(!line.startsWith("c")){
                    if(clausesCount-- >= 0) {
                        String[] blocks = line.split(" ");
                        List<Term> clauseTerms = new ArrayList<>();
                        for (String block : blocks) {
                            int index = Integer.parseInt(block);
                            if(index != 0)
                                clauseTerms.add(new Term(variables.get(Math.abs(index)), index > 0));
                        }
                        clauseList.add(new Clause(clauseTerms));
                    }
                    else{
                        String[] blocks = line.split(" ");
                        for(int i = 0; i < blocks.length; i++){
                            variables.get(i+1).setWeight(Integer.parseInt(blocks[i]));
                        }
                    }
                }
            }
            formulas.add(new Formula(formulaId, clauseList, variables));
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be processed.");
        }
        return formulas;
    }
}

