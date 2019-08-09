package com.company;

import java.io.*;
import java.util.*;

public class CountStatistics {

//        args[0] -- solution file
//        args[1] -- time file
//        args[2] -- solution stat file
//        args[3] -- time stat file
    public static void main(String[] args) throws IOException {

        if (args.length > 3) {
            String solutionFile = args[0];
            String timeFile = args[1];
            String solutionStatFile = args[2];
            String timeStatFile = args[3];

            List<Integer> solutions = readSolutionFile(solutionFile);
            OptionalDouble average = solutions.stream()
                    .filter(a -> a != 0)
                    .mapToInt(a -> a)
                    .average();

            long sum = solutions.stream()
                    .filter(a -> a == 0)
                    .mapToInt(a -> a)
                    .count();

            if (average.isPresent()) {
                writeToFileSolution(average.getAsDouble(), solutionStatFile);
            }
            writeToFile(sum, solutionStatFile);


            List<Double> times = readTimeFile(timeFile);
            average = times.stream()
                    .mapToDouble(a -> a)
                    .average();
            if(average.isPresent()){
                writeToFileTime(average.getAsDouble(), timeStatFile);
            }
        }
    }

    private static void writeToFileSolution(Double value, String file) throws IOException {
        String time = String.format("%.4f", value);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(time);
        bw.close();
    }

    private static void writeToFileTime(Double value, String file) throws IOException {
        String time = String.format("%,4f", value);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(time);
        bw.newLine();
        bw.close();
    }

    private static void writeToFile(long value, String file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("\t" + value);
        bw.newLine();
        bw.close();
    }

    private static List<Double> readTimeFile(String solutionFile) {
        List<Double> times = new ArrayList<>();
        try {
            File file = new File(solutionFile);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                times.add(Double.parseDouble(line));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be processed.");
        }
        return times;
    }

    private static List<Integer> readSolutionFile(String solutionFile) {
        List<Integer> solutions = new ArrayList<>();
        try {
            File file = new File(solutionFile);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                solutions.add(Integer.parseInt(line));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be processed.");
        }
        return solutions;
    }


}
