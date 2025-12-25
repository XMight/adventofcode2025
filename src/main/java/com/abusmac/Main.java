package com.abusmac;

import com.abusmac.advent2025.Day1;
import com.abusmac.math.MathUtils;
import com.abusmac.types.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("Hello and welcome to advent 2025!");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(Objects.requireNonNull(classLoader.getResource("day1/day1input.txt")).getFile());

        String fileData = Files.readString(path);

        List<Integer> list = fileData.lines().map(s -> (Integer.parseInt(s.substring(1))) * (s.charAt(0) == 'L' ? -1 : 1)).toList();

        Day1 day1 = new Day1(list, true);
        Integer day1Result = day1.call();

        if(day1Result != 1118) {
            throw new Exception("Day1 day1result is wrong: " + day1Result);
        }

        final int SAMPLES_COUNT = 100;
        List<Long> executionDurations = new ArrayList<>(SAMPLES_COUNT);

        LOGGER.info("OOP false");
        runDay1WithIntegerListAndNoOOP(SAMPLES_COUNT, executionDurations, list, false);
        runDay1WithPathAndNoOOP(SAMPLES_COUNT, executionDurations, path, false);
        runDay1WithStringAndNoOOP(SAMPLES_COUNT, executionDurations, fileData, false);

        LOGGER.info("OOP true");
        runDay1WithIntegerListAndNoOOP(SAMPLES_COUNT, executionDurations, list, true);
        runDay1WithPathAndNoOOP(SAMPLES_COUNT, executionDurations, path, true);
        runDay1WithStringAndNoOOP(SAMPLES_COUNT, executionDurations, fileData, true);
    }

    private static void runDay1WithIntegerListAndNoOOP(int samplesCount, List<Long> executionDurations, List<Integer> list, boolean useOOP) throws Exception {
        LOGGER.info("Executing Day1 with list");
        executionDurations.clear();

        for(int i = 0; i < samplesCount; i++) {
            executionDurations.add(runWithProfiling(new Day1(list, useOOP)));
        }

        Triplet<String, String, String> stats = MathUtils.calculateStats(executionDurations);
        LOGGER.info("Min: {}; Max: {}; Median: {}", stats.f, stats.s, stats.t);
    }

    private static void runDay1WithPathAndNoOOP(int samplesCount, List<Long> executionDurations, Path path, boolean useOOP) throws Exception {
        LOGGER.info("Executing Day1 with path");
        executionDurations.clear();

        for(int i = 0; i < samplesCount; i++) {
            executionDurations.add(runWithProfiling(new Day1(path, useOOP)));
        }

        Triplet<String, String, String> stats = MathUtils.calculateStats(executionDurations);
        LOGGER.info("Min: {}; Max: {}; Median: {}", stats.f, stats.s, stats.t);
    }

    private static void runDay1WithStringAndNoOOP(int samplesCount, List<Long> executionDurations, String input, boolean useOOP) throws Exception {
        LOGGER.info("Executing Day1 with string data");
        executionDurations.clear();

        for(int i = 0; i < samplesCount; i++) {
            executionDurations.add(runWithProfiling(new Day1(input, useOOP)));
        }

        Triplet<String, String, String> stats = MathUtils.calculateStats(executionDurations);
        LOGGER.info("Min: {}; Max: {}; Median: {}", stats.f, stats.s, stats.t);
    }

    private static long runWithProfiling(Callable<Integer> callable) throws Exception {
        long before = System.nanoTime();

        Integer day1Result = callable.call();

        long after = System.nanoTime();

        return after - before;
    }
}
