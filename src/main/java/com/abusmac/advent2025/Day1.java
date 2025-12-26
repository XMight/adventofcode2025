package com.abusmac.advent2025;

import com.abusmac.math.MathUtils;
import com.abusmac.types.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Day1 implements Callable<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day1.class);

    private List<Integer> integerListInput;
    private String inputNonParsedString;
    private Path path;
    private final boolean useOOP;

    public Day1(String input, boolean useOOP) {
        this.inputNonParsedString = input;
        this.useOOP = useOOP;
    }

    public Day1(List<Integer> input, boolean useOOP) {
        integerListInput = input;
        this.useOOP = useOOP;
    }

    public Day1(Path path, boolean useOOP) {
        this.path = path;
        this.useOOP = useOOP;
    }

    @Override
    public Integer call() throws IOException {
        if (useOOP) {
            if (integerListInput != null) {
                return callOopOnIntegerList();
            } else if (inputNonParsedString != null) {
                return callOopOnNonParsedString();
            } else if (path != null) {
                return callOopOnPath();
            }
        } else {
            if (integerListInput != null) {
                return callOnIntegerList();
            } else if (inputNonParsedString != null) {
                return callOnNonParsedString();
            } else if (path != null) {
                return callOnPath();
            }
        }

        return null;
    }

    private Integer callOopOnIntegerList() {
        Dial dial = new Dial(50);

        for (int i = 0; i < integerListInput.size(); i++) {
            dial.rotate(integerListInput.get(i));
        }

        return dial.zeroHits;
    }

    private Integer callOopOnNonParsedString() {
        String[] strings = inputNonParsedString.split("\n");

        Dial dial = new Dial(50);

        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            dial.rotate((Integer.parseInt(string.substring(1))) * (string.charAt(0) == 'L' ? -1 : 1));
        }

        return dial.zeroHits;
    }

    private Integer callOopOnPath() throws IOException {
        String fileData = Files.readString(path);

        integerListInput = fileData.lines().map(
                s -> (Integer.parseInt(s.substring(1))) * (s.charAt(0) == 'L' ? -1 : 1)).toList();

        return callOopOnIntegerList();
    }

    private Integer callOnPath() throws IOException {
        String fileData = Files.readString(path);

        integerListInput = fileData.lines().map(
                s -> (Integer.parseInt(s.substring(1))) * (s.charAt(0) == 'L' ? -1 : 1)).toList();

        return callOnIntegerList();
    }

    private Integer callOnNonParsedString() {
        int zeroHits = 0;
        String[] strings = inputNonParsedString.split("\n");
        int position = 50;

        for (int i = 0; i < strings.length; i++) {
            String rotationStr = strings[i];

            int rotation = (Integer.parseInt(rotationStr.substring(1))) * (rotationStr.charAt(0) == 'L' ? -1 : 1);

            int remaining = rotation % 100;

            int calculation = position + remaining;
            if (calculation < 0) {
                //System.out.println("Before Position: " + this.position + " Rotation: " + rotation);
                position = 100 + calculation;
                //System.out.println("After Position: " + this.position);
            } else if (calculation > 99) {
                //System.out.println("Before Position: " + this.position + " Rotation: " + rotation);
                position = calculation - 100;
                //System.out.println("After Position: " + this.position);
            } else {
                position = calculation;
            }

            if (position == 0) {
                zeroHits++;
            }
        }

        return zeroHits;
    }

    private int callOnIntegerList() {
        int zeroHits = 0;
        int position = 50;

        for (int i = 0; i < integerListInput.size(); i++) {
            int rotation = integerListInput.get(i);
            int remaining = rotation % 100;

            int calculation = position + remaining;
            if (calculation < 0) {
                //System.out.println("Before Position: " + this.position + " Rotation: " + rotation);
                position = 100 + calculation;
                //System.out.println("After Position: " + this.position);
            } else if (calculation > 99) {
                //System.out.println("Before Position: " + this.position + " Rotation: " + rotation);
                position = calculation - 100;
                //System.out.println("After Position: " + this.position);
            } else {
                position = calculation;
            }

            if (position == 0) {
                zeroHits++;
            }
        }

        return zeroHits;
    }

    public static class Dial {
        private int position = 0;
        private int zeroHits = 0;

        public Dial(int start) {
            this.position = start;
        }

        public void rotate(int rotation) {
            int remaining = rotation % 100;
            //mod = mod * Integer.signum(rotation);

            int calculation = position + remaining;
            if (calculation < 0) {
                //System.out.println("Before Position: " + this.position + " Rotation: " + rotation);
                position = 100 + calculation;
                //System.out.println("After Position: " + this.position);
            } else if (calculation > 99) {
                //System.out.println("Before Position: " + this.position + " Rotation: " + rotation);
                position = calculation - 100;
                //System.out.println("After Position: " + this.position);
            } else {
                position = calculation;
            }

            if (position == 0) {
                zeroHits++;
            }
        }
    }

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
        runDay1WithIntegerList(SAMPLES_COUNT, executionDurations, list, false);
        runDay1WithPath(SAMPLES_COUNT, executionDurations, path, false);
        runDay1WithString(SAMPLES_COUNT, executionDurations, fileData, false);

        LOGGER.info("OOP true");
        runDay1WithIntegerList(SAMPLES_COUNT, executionDurations, list, true);
        runDay1WithPath(SAMPLES_COUNT, executionDurations, path, true);
        runDay1WithString(SAMPLES_COUNT, executionDurations, fileData, true);
    }

    private static void runDay1WithIntegerList(int samplesCount, List<Long> executionDurations, List<Integer> list, boolean useOOP) throws Exception {
        LOGGER.info("Executing Day1 with list");
        executionDurations.clear();

        for(int i = 0; i < samplesCount; i++) {
            executionDurations.add(runWithProfiling(new Day1(list, useOOP)));
        }

        Triplet<String, String, String> stats = MathUtils.calculateStats(executionDurations);
        LOGGER.info("Min: {}; Max: {}; Median: {}", stats.f, stats.s, stats.t);
    }

    private static void runDay1WithPath(int samplesCount, List<Long> executionDurations, Path path, boolean useOOP) throws Exception {
        LOGGER.info("Executing Day1 with path");
        executionDurations.clear();

        for(int i = 0; i < samplesCount; i++) {
            executionDurations.add(runWithProfiling(new Day1(path, useOOP)));
        }

        Triplet<String, String, String> stats = MathUtils.calculateStats(executionDurations);
        LOGGER.info("Min: {}; Max: {}; Median: {}", stats.f, stats.s, stats.t);
    }

    private static void runDay1WithString(int samplesCount, List<Long> executionDurations, String input, boolean useOOP) throws Exception {
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
