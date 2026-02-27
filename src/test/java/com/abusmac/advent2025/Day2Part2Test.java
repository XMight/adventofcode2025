package com.abusmac.advent2025;

import com.abusmac.io.InputOutputUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class Day2Part2Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day2Test.class);

    @Test
    void testDay2ExampleScenario() {
        String testData = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
        Day2Part2 day2 = new Day2Part2(testData);
        Long result = day2.call();

        Assertions.assertEquals(4174379265L, result);
    }

    @Test
    void testDay2SingleInputRange5() {
        String range = "11-22"; // 33
        Day2Part2 day2 = new Day2Part2(range);
        Long result = day2.call();

        Assertions.assertEquals(33L, result);
    }

    @Test
    void testDay2SingleInputRange95_115() {
        String range = "95-115"; // 210
        Day2Part2 day2 = new Day2Part2(range);
        Long result = day2.call();

        Assertions.assertEquals(210L, result);
    }

    @Test
    void testDay2SingleInputRange100_115() {
        String range = "100-115"; // 111
        Day2Part2 day2 = new Day2Part2(range);
        Long result = day2.call();

        Assertions.assertEquals(111L, result);
    }

    @Test
    void testDay2SingleInputRange998_1012() {
        String range = "998-1012"; // 2009
        Day2Part2 day2 = new Day2Part2(range);
        Long result = day2.call();

        Assertions.assertEquals(2009L, result);
    }

    @Test
    void testDay2SingleInputRange1188511880_1188511890() {
        String range = "1188511880-1188511890"; // 1188511885
        Day2Part2 day2 = new Day2Part2(range);
        Long result = day2.call();

        Assertions.assertEquals(1188511885L, result);
    }

    @Test
    void testDay2SingleInputRange9() throws IOException {
        String fileData = InputOutputUtils.readResourceFileData("day2/day2input.txt");

        Day2Part2 day2 = new Day2Part2(fileData, true);
        Long result = day2.call();

        LOGGER.info("Day2Part2 result: {}", result);

        Assertions.assertEquals(48778605167L, result);
    }

    @Test
    void testWithProfiling() throws Exception {
        String fileData = InputOutputUtils.readResourceFileData("day2/day2input.txt");

        LOGGER.info("Executing Day2Part2 with string data from test");
        Day2.runDay2(fileData, 100);
    }
}