package com.sat.helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class CPUTimer {

    private long start;

    private List<Double> measurings;

    public CPUTimer() {
        this.measurings = new ArrayList<>();
    }

    public void start() {
        start = getCpuTime();
    }

    public void stop() {
        long end = getCpuTime();
        double seconds = (double) (end - start) / 1000000000.0;
        measurings.add(seconds);
    }

    private double getAverageTime() {
        OptionalDouble average = measurings.stream()
                .mapToDouble(a -> a)
                .average();
        if (average.isPresent())
            return average.getAsDouble();
        return Double.NaN;
    }

    public void writeAverageTime() throws IOException {
        String time = String.format ("%.5f", getAverageTime());
        FileWriter fw = new FileWriter("time/averageTimes.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(time + "\n");
        bw.close();
    }

    /**
     * Get CPU time in nanoseconds.
     */
    private static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

}