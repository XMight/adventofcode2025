package com.abusmac.advent2025;

import com.abusmac.io.InputOutputUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class Day2 implements Callable<Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day2.class);

    private final String input;

    public Day2(String input) {
        this.input = input;
    }

    @Override
    public Long call() {
        long sum = 0;
        String[] idRanges = input.split(",");
        Map<String, List<Long>> resultsMap = new HashMap<>();

        for (int i = 0; i < idRanges.length; i++) {
            long result = sumInvalidIDs(idRanges[i], resultsMap);

            if (result == 0L) {
                LOGGER.info(String.format("Range has no invalid IDs: %s", idRanges[i]));
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

        LOGGER.info("Processing range " + idRange);

        int startNumberLength = idStart.length();

        if (startNumberLength == idEnd.length()) {
            if (startNumberLength % 2 == 0) {
                long sum = sumInvalidIdsForSameLengthRange(idStart, idEnd, results);

                resultsMap.putIfAbsent(idRange, results);

                return sum;
            }
        } else {
            // This code works only if the ranges differ by a maximum of 1 digit and start is smaller than end
            // Starts with even integer digit number
            if (startNumberLength % 2 == 0) {
                long newEnd = (long) Math.pow(10, startNumberLength + 1) - 1;

                long sum = sumInvalidIdsForSameLengthRange(idStart, String.valueOf(newEnd), results);

                resultsMap.putIfAbsent(idRange, results);

                return sum;
            } else {
                long i = Long.parseLong(idEnd, 0, 1, 10);
                long newStart = i * (long) Math.pow(10, idEnd.length() - 1);

                long sum = sumInvalidIdsForSameLengthRange(String.valueOf(newStart), idEnd, results);

                resultsMap.putIfAbsent(idRange, results);

                return sum;
            }
        }

        return 0;
    }

    private long sumInvalidIdsForSameLengthRange(String idStart, String idEnd, List<Long> results) {
        long sum = 0;
        int len = idStart.length();

        String startLeft = idStart.substring(0, len / 2);
        String startRight = idStart.substring(len / 2, len);

        long sLeft = Long.parseLong(startLeft);
        long sRight = Long.parseLong(startRight);

        String endLeft = idEnd.substring(0, len / 2);
        String endRight = idEnd.substring(len / 2, len);

        long eLeft = Long.parseLong(endLeft);
        long eRight = Long.parseLong(endRight);

        long i = sLeft;

        if (i < eLeft) {
            if (i >= sRight) {
                long newInvalidId = Long.parseLong(i + String.valueOf(i));

                LOGGER.info("New invalid id " + newInvalidId);

                sum += newInvalidId;
                results.add(newInvalidId);
            }
            i++;
        }

        if (i < eLeft) {
            for (; i <= eLeft - 1; i++) {
                long newInvalidId = Long.parseLong(i + String.valueOf(i));

                LOGGER.info("New invalid id " + newInvalidId);

                sum += newInvalidId;
                results.add(newInvalidId);
            }
        }

        if(sLeft != eLeft) {
            for (; i <= eRight && i <= eLeft; i++) {
                long newInvalidId = Long.parseLong(i + String.valueOf(i));
                LOGGER.info("New invalid id " + newInvalidId);
                sum += newInvalidId;
                results.add(newInvalidId);
            }
        } else {
            if (i <= eRight && i >= sRight) {
                long newInvalidId = Long.parseLong(i + String.valueOf(i));

                LOGGER.info("New invalid id " + newInvalidId);

                sum += newInvalidId;
                results.add(newInvalidId);
            }
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        String fileData = InputOutputUtils.readResourceFileData("day2/day2inputstripped.txt");

        Day2 day2 = new Day2(fileData);
        Long result = day2.call();

        LOGGER.info("Result of Day2 is: {}", result);
    }
}
