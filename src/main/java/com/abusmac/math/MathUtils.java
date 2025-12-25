package com.abusmac.math;

import com.abusmac.types.Triplet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MathUtils {
    public static <T extends Number> double findStandardDeviation(List<T> input, double mean) {
        double result = 0.0;

        double sum = 0.0;
        for (var i : input) {
            sum += Math.pow(i.doubleValue() - mean, 2);
        }

        result = Math.sqrt(sum / input.size());

        return result;
    }

    public static <T extends Number> double findMean(T sum, long size) {
        return size == 0 ? 0.0 : sum.doubleValue() / size;
    }

    public static double findMedianForLong(List<Long> input) {
        input.sort(Comparator.naturalOrder());

        double median = 0.0;
        int size = input.size();
        if (size >= 2) {
            if (size % 2 == 0)
                median = ((double) input.get(size / 2) + (double) input.get(size / 2 - 1)) / 2.0;
            else
                median = (double) input.get(size / 2);
        } else if (size == 1) {
            median = input.get(0);
        }

        return median;
    }

    public static double findMedianForDouble(List<Double> input) {
        input.sort(Comparator.comparingDouble(e -> e.doubleValue()));

        double median = 0.0;
        int size = input.size();
        if (size >= 2) {
            if (size % 2 == 0)
                median = (input.get(size / 2) + (double) input.get(size / 2 - 1)) / 2.0;
            else
                median = input.get(size / 2);
        } else if (size == 1) {
            median = input.get(0);
        }

        return median;
    }

    public static Triplet<String, String, String> calculateStats(List<Long> input) {
        input.sort(Comparator.naturalOrder());

        double medianForLong = findMedianForLong(input);

        return new Triplet<>(nanoToHumanReadableString(input.get(0)), nanoToHumanReadableString(input.get(input.size() - 1)), nanoToHumanReadableString(medianForLong));
    }

    public static String nanoToHumanReadableString(double durationInNs) {
        long seconds = (long) (durationInNs / 1_000_000_000L);
        long ms = (long) ((durationInNs % 1_000_000_000L) / 1_000_000L);
        long mu = (long) ((durationInNs % 1_000_000L) / 1_000L);
        long ns = (long) (durationInNs % 1_000L);

        StringBuilder formatBuilder = new StringBuilder();
        List<Object> args = new ArrayList<>();
        if(seconds > 0) {
            formatBuilder.append(String.format("%ds ", seconds));
            args.add(seconds);
        }

        if(ms > 0) {
            formatBuilder.append(String.format("%dms ", ms));
            args.add(ms);
        }

        if(mu > 0) {
            formatBuilder.append(String.format("%dµs ", mu));
            args.add(mu);
        }

        if(ns > 0) {
            formatBuilder.append(String.format("%dns ", ns));
            args.add(ns);
        }

        return String.format(formatBuilder.toString(), args.toArray());
    }
}
