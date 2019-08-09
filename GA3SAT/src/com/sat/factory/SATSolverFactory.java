package com.sat.factory;

import com.sat.algorithm.SATSolverGA;
import com.sun.istack.internal.NotNull;

import java.util.Map;

public class SATSolverFactory {
    @NotNull
    public static SATSolver getSolver(String algorithm, Map<String, String> parameters) {
        switch (algorithm) {
            case "ga":
                return new SATSolverGA(parameters);

            default:
                return new SATSolverGA(parameters);
        }
    }
}
