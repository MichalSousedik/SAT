package com.sat.factory;

import com.sat.Formula;
import com.sat.algorithm.SATSolution;

public interface SATSolver {

    SATSolution solve(Formula formula);

}
