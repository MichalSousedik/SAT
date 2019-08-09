#!/bin/bash

TYPE="ga";
COUNT_TIME=true;
INTERATIONS=1;
GENERATION=100;
POPULATION=10;
TOURNAMENT_SIZE=6;
CROSSOVER_PROBABILITY=0.8; #0..1
MUTATION_RATE=0.1; #0..1
ELITISM=4;
TERMS=30;
SOLUTION_STATS="solution/solutions_stats.txt";
TIME_STATS="solution/time_stats.txt";
SOLUTION_FILE="solution/solution.txt";
TIME_FILE="time/averageTimes.txt";

[ -e $SOLUTION_FILE ] && rm $SOLUTION_FILE
[ -e $TIME_FILE ] && rm $TIME_FILE
[ -e $SOLUTION_STATS ] && rm $SOLUTION_STATS
[ -e $TIME_STATS ] && rm $TIME_STATS


#echo "Generation count:" >> $SOLUTION_STATS;
#echo "Generation count:" >> $TIME_STATS;
#for GENERATION in 50 100 150 200 250 300
#do
#   for INDEX in 1 2 3 4 5 6 7 8 9 10
#   do
#       java -jar GA3SAT/out/artifacts/GA3SAT/GA3SAT.jar instances/sat_${TERMS}_$INDEX.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM >> $SOLUTION_FILE
#   done
#
#java -jar Statistics/out/artifacts/Statistics/Statistics.jar $SOLUTION_FILE $TIME_FILE $SOLUTION_STATS $TIME_STATS
#
#[ -e $SOLUTION_FILE ] && rm $SOLUTION_FILE
#[ -e $TIME_FILE ] && rm $TIME_FILE
#
#done

#echo "Population count:" >> $SOLUTION_STATS;
#echo "Population count:" >> $TIME_STATS;
#for POPULATION in 2 4 6 8 10 12
#do
#for INDEX in 1 2 3 4 5 6 7 8 9 10
#do
#java -jar GA3SAT/out/artifacts/GA3SAT/GA3SAT.jar instances/sat_${TERMS}_$INDEX.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM >> $SOLUTION_FILE
#done

#java -jar Statistics/out/artifacts/Statistics/Statistics.jar $SOLUTION_FILE $TIME_FILE $SOLUTION_STATS $TIME_STATS

#done

#echo "Crossover probability:" >> $SOLUTION_STATS;
#echo "Crossover probability:" >> $TIME_STATS;
#for CROSSOVER_PROBABILITY in 0.2 0.4 0.5 0.6 0.8 0.9
#do
#for INDEX in 1 2 3 4 5 6 7 8 9 10
#do
#java -jar GA3SAT/out/artifacts/GA3SAT/GA3SAT.jar instances/sat_${TERMS}_$INDEX.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM >> $SOLUTION_FILE
#done

#java -jar Statistics/out/artifacts/Statistics/Statistics.jar $SOLUTION_FILE $TIME_FILE $SOLUTION_STATS $TIME_STATS

#done

#echo "Mutation rate:" >> $SOLUTION_STATS;
#echo "Mutation rate:" >> $TIME_STATS;
#for MUTATION_RATE in 0.01 0.05 0.1 0.15 0.2 0.25
#do
#for INDEX in 1 2 3 4 5 6 7 8 9 10
#do
#java -jar GA3SAT/out/artifacts/GA3SAT/GA3SAT.jar instances/sat_${TERMS}_$INDEX.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM >> $SOLUTION_FILE
#done
#
#java -jar Statistics/out/artifacts/Statistics/Statistics.jar $SOLUTION_FILE $TIME_FILE $SOLUTION_STATS $TIME_STATS
#
#[ -e $SOLUTION_FILE ] && rm $SOLUTION_FILE
#[ -e $TIME_FILE ] && rm $TIME_FILE
#
#done
#
#echo "Tournament size:" >> $SOLUTION_STATS;
#echo "Tournament size:" >> $TIME_STATS;
#for TOURNAMENT_SIZE in 5 10 15 20 25 30 35 40 45 50
#do
#for INDEX in 1 2 3 4 5 6 7 8 9 10
#do
#java -jar GA3SAT/out/artifacts/GA3SAT/GA3SAT.jar instances/sat_${TERMS}_$INDEX.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM >> $SOLUTION_FILE
#done
#
#java -jar Statistics/out/artifacts/Statistics/Statistics.jar $SOLUTION_FILE $TIME_FILE $SOLUTION_STATS $TIME_STATS
#[ -e $SOLUTION_FILE ] && rm $SOLUTION_FILE
#[ -e $TIME_FILE ] && rm $TIME_FILE
#done


echo "Elites:" >> $SOLUTION_STATS;
echo "Elites:" >> $TIME_STATS;
for ELITISM in 2 4 6 8 10 12 14 16 18 20 22 24 26 28 30
do
for INDEX in 1 2 3 4 5 6 7 8 9 10
do
java -jar GA3SAT/out/artifacts/GA3SAT/GA3SAT.jar instances/sat_${TERMS}_$INDEX.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM >> $SOLUTION_FILE
done

java -jar Statistics/out/artifacts/Statistics/Statistics.jar $SOLUTION_FILE $TIME_FILE $SOLUTION_STATS $TIME_STATS
[ -e $SOLUTION_FILE ] && rm $SOLUTION_FILE
[ -e $TIME_FILE ] && rm $TIME_FILE
done

#GENERATION=1000;
#POPULATION=20;
#TOURNAMENT_SIZE=6;
#CROSSOVER_PROBABILITY=0.8; #0..1
#MUTATION_RATE=0.9; #0..1
#ELITISM=4;
#CLAUSE_RATE=2;
#
#echo "Terms count:" >> $SOLUTION_STATS;
#echo "Terms count:" >> $TIME_STATS;
#for TERMS in 120 150 180
#do
#
#./generate.sh $CLAUSE_RATE $TERMS
#
#for  ((INDEX = 1; INDEX <= 10; INDEX++))
#do
#java -jar GA3SAT/out/artifacts/GA3SAT/GA3SAT.jar instances/sat_${TERMS}_$INDEX.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM >> $SOLUTION_FILE
#done
#
#java -jar Statistics/out/artifacts/Statistics/Statistics.jar $SOLUTION_FILE $TIME_FILE $SOLUTION_STATS $TIME_STATS
#
#[ -e $SOLUTION_FILE ] && rm $SOLUTION_FILE
#[ -e $TIME_FILE ] && rm $TIME_FILE
#done


#echo "Clause rate:" >> $SOLUTION_STATS;
#echo "Clause rate:" >> $TIME_STATS;
#for CLAUSE_RATE in 1 2 3 4 5 6 7 8 9 10
#do
#
#./generate.sh $CLAUSE_RATE 30
#
#for  ((INDEX = 1; INDEX <= 10; INDEX++))
#do
#java -jar GA3SAT/out/artifacts/GA3SAT/GA3SAT.jar instances/sat_${TERMS}_$INDEX.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM >> $SOLUTION_FILE
#done
#
#java -jar Statistics/out/artifacts/Statistics/Statistics.jar $SOLUTION_FILE $TIME_FILE $SOLUTION_STATS $TIME_STATS
#
#[ -e $SOLUTION_FILE ] && rm $SOLUTION_FILE
#[ -e $TIME_FILE ] && rm $TIME_FILE
#done
