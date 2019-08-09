#!/bin/bash
SAT_RATIO=$1;
MAX_WEIGHT=100;
MAX_TERMS=3;
NEGATIVE_PROBABILITY=0.3;
TERMS=$2
for ((INDEX = 1; INDEX <= 10; INDEX++))
do
[ -e instances/sat_${TERMS}_$INDEX.dat ] && rm instances/sat_${TERMS}_$INDEX.dat

CLAUSES=$(echo "($TERMS * $SAT_RATIO) / 1" | bc)
java -jar SATGenerator/out/artifacts/SATGenerator/SATGenerator.jar  $CLAUSES $TERMS $MAX_WEIGHT $MAX_TERMS $NEGATIVE_PROBABILITY >> instances/sat_${TERMS}_$INDEX.dat

done
