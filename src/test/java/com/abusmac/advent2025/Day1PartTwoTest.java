package com.abusmac.advent2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day1PartTwoTest {
    @Test
    void test1_success_day1Part2() {
        String rotations = "L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82";

        Day1PartTwo day1PartTwo = new Day1PartTwo(rotations);
        Integer result = day1PartTwo.call();

        Assertions.assertEquals(6, result);
    }

    @Test
    void test2_multipleRotations_day1Part2() {
        String rotations = "L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82\nR200";

        Day1PartTwo day1PartTwo = new Day1PartTwo(rotations);
        Integer result = day1PartTwo.call();

        Assertions.assertEquals(8, result);
    }

    @Test
    void test3_zeroRotation_day1Part2() {
        String rotations = "L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82\nR0";

        Day1PartTwo day1PartTwo = new Day1PartTwo(rotations);
        Integer result = day1PartTwo.call();

        Assertions.assertEquals(6, result);
    }

    @Test
    void test4_rotationStopOnZero_day1Part2() {
        String rotations = "L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82\nR68\nR201";

        Day1PartTwo day1PartTwo = new Day1PartTwo(rotations);
        Integer result = day1PartTwo.call();

        Assertions.assertEquals(9, result);
    }

    @Test
    void test4_rotationStopOnZero2_day1Part2() {
        String rotations = "L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82\nR68\nL100";

        Day1PartTwo day1PartTwo = new Day1PartTwo(rotations);
        Integer result = day1PartTwo.call();

        Assertions.assertEquals(8, result);
    }

    @Test
    void test4_rotationStopOnZero3_day1Part2() {
        String rotations = "L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82\nR68\nL300";

        Day1PartTwo day1PartTwo = new Day1PartTwo(rotations);
        Integer result = day1PartTwo.call();

        Assertions.assertEquals(10, result);
    }
    @Test
    void test4_rotationStopOnZero4_day1Part2() {
        String rotations = "L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82\nR68\nL301\nR1";

        Day1PartTwo day1PartTwo = new Day1PartTwo(rotations);
        Integer result = day1PartTwo.call();

        Assertions.assertEquals(11, result);
    }
}