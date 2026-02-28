package com.abusmac.advent2025;

import com.abusmac.io.InputOutputUtils;
import com.abusmac.math.MathUtils;
import com.abusmac.string.StringUtils;
import com.abusmac.types.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;

public class Day2Part2 implements Callable<Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day2Part2.class);

    private final String input;
    private final boolean enableLogs;

    public Day2Part2(String input, boolean enableLogs) {
        this.input = input;
        this.enableLogs = enableLogs;
    }

    public Day2Part2(String input) {
        this(input, false);
    }

    public static void main(String[] args) throws Exception {
        String fileData = InputOutputUtils.readResourceFileData("day2/day2inputstripped.txt");

        LOGGER.info("Executing Day2 Part2 with string data");
        runDay2Part2(fileData, 100);
    }

    static void runDay2Part2(String input, int samplesCount) throws Exception {
        List<Long> executionDurations = new ArrayList<>();

        for (int i = 0; i < samplesCount; i++) {
            executionDurations.add(runWithProfiling(new Day2Part2(input)));
        }

        Triplet<String, String, String> stats = MathUtils.calculateStats(executionDurations);
        LOGGER.info("Min: {}; Max: {}; Median: {}", stats.f, stats.s, stats.t);
    }

    static long runWithProfiling(Callable<? extends Number> callable) throws Exception {
        long before = System.nanoTime();

        Number result = callable.call();

        long after = System.nanoTime();

        return after - before;
    }

    @Override
    public Long call() {
        long sum = 0;
        String[] idRanges = input.split(",");
        Map<String, List<Long>> resultsMap = new HashMap<>();

        for (int i = 0; i < idRanges.length; i++) {
            long result = sumInvalidIDs(idRanges[i], resultsMap);

            if (result == 0L) {
                if (enableLogs) {
                    LOGGER.info(String.format("Range has no invalid IDs: %s", idRanges[i]));
                }
            }

            sum += result;
        }

        return sum;
    }

    private long sumInvalidIDs(String idRange, Map<String, List<Long>> resultsMap) {
        String[] idRangeStartEnd = idRange.split("-");
        List<Long> results = new ArrayList<>();

        String idStart = idRangeStartEnd[0];
        String idEnd = idRangeStartEnd[1];

        long idStartInt = Long.parseLong(idStart);
        long idEndInt = Long.parseLong(idEnd);

        // To detect invalid data within the dataset
        if (idStartInt > idEndInt) {
            throw new IllegalArgumentException("Invalid ID range " + idRange);
        }

        if (enableLogs) {
            LOGGER.info("Processing range " + idRange);
        }

        int startNumberLength = idStart.length();

        if (startNumberLength == idEnd.length()) {
            long sum = sumInvalidIdsForSameLengthRange(idStart, idEnd, results);

            resultsMap.putIfAbsent(idRange, results);

            return sum;
        } else {
            // This code works only if the ranges differ by a maximum of 1 digit and start is smaller than end
            // Starts with even integer digit number
            long newEndStart = (long) Math.pow(10, startNumberLength);
            long newStartEnd = newEndStart - 1;

            long sum = sumInvalidIdsForSameLengthRange(idStart, String.valueOf(newStartEnd), results);
            sum += sumInvalidIdsForSameLengthRange(String.valueOf(newEndStart), idEnd, results);

            resultsMap.putIfAbsent(idRange, results);

            return sum;
        }
    }

    private long sumInvalidIdsForSameLengthRange(String idStart, String idEnd, List<Long> results) {
        long sum = 0;
        int len = idStart.length();

        long i = Long.parseLong(idStart);
        long idEndInt = Long.parseLong(idEnd);

        for (; i <= idEndInt; i++) {
            if (isInvalid(i, len)) {
                results.add(i);
                sum += i;
            }
        }

        return sum;
    }

    private boolean isInvalid(long id, int len) {
        boolean result = false;

        String s = String.valueOf(id);

        for (int i = len - 1; i >= 1; i--) {
            List<String> numbers = StringUtils.splitIntoSameLengthNumbers(s, len, i);

            result = true;
            String current = numbers.get(0);
            for (int k = 0; k < numbers.size(); k++) {
                if (current.equalsIgnoreCase(numbers.get(k))) {
                    current = numbers.get(k);
                } else {
                    result = false;
                    break;
                }
            }

            if (result) {
                break;
            }
        }

        return result;
    }
}
