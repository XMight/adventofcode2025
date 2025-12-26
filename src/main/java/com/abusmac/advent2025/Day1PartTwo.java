package com.abusmac.advent2025;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Day1PartTwo implements Callable<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day1PartTwo.class);

    private final String inputNonParsedString;

    public Day1PartTwo(String inputNonParsedString) {
        this.inputNonParsedString = inputNonParsedString;
    }

    @Override
    public Integer call() {
        String[] strings = inputNonParsedString.split("\n");

        Day1PartTwo.Dial dial = new Day1PartTwo.Dial(50);

        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            dial.rotate((Integer.parseInt(string.substring(1))) * (string.charAt(0) == 'L' ? -1 : 1));
        }

        return dial.zeroHits;
    }

    public static class Dial {
        private int position = 0;
        private int zeroHits = 0;

        public Dial(int start) {
            this.position = start;
        }

        public void rotate(int rotation) {
            int remaining = rotation % 100;
            int zeroHitsDuringRotation = Math.abs(rotation / 100);

            int calculation = position + remaining;
            if (calculation < 0) {
                zeroHits = position != 0 ? zeroHits + 1 : zeroHits;
                position = 100 + calculation;
            } else if (calculation > 99) {
                zeroHits++;
                position = calculation - 100;
            } else {
                if(position != 0) {
                    position = calculation;
                    if(calculation == 0) {
                        zeroHits++;
                    }
                } else {
                    position = calculation;
                }
            }

            zeroHits += zeroHitsDuringRotation;
        }
    }

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(Objects.requireNonNull(classLoader.getResource("day1/day1input.txt")).getFile());
        String fileData = Files.readString(path);

        Day1PartTwo day1PartTwo = new Day1PartTwo(fileData);
        Integer result = day1PartTwo.call();

        LOGGER.info("Day1PartTwo result: {}", result);
    }
}
