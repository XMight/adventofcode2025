package com.abusmac.advent2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day2Test {

    @Test
    void testDay2ExampleScenario() {
        String testData = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
        Day2 day2 = new Day2(testData);
        Long result = day2.call();

        Assertions.assertEquals(1227775554L, result);
    }

    @Test
    void testDay2SingleInputRange() {
        String range = "7171599589-7171806875"; // 71716, 71717
        Day2 day2 = new Day2(range);
        Long result = day2.call();

        Assertions.assertEquals(14343443433L, result);
    }

    @Test
    void testDay2SingleInputRange2() {
        String range = "726-1031"; // 1010
        Day2 day2 = new Day2(range);
        Long result = day2.call();

        Assertions.assertEquals(1010L, result);
    }

    @Test
    void testDay2SingleInputRange3() {
        String range = "1039-2064"; // 1010
        Day2 day2 = new Day2(range);
        Long result = day2.call();

        Assertions.assertEquals(15655L, result);
    }

    @Test
    void testDay2SingleInputRange4() {
        String range = "565653-565659"; // 0
        Day2 day2 = new Day2(range);
        Long result = day2.call();

        Assertions.assertEquals(0L, result);
    }

    @Test
    void testDay2SingleInputRange5() {
        String range = "11-22"; // 33
        Day2 day2 = new Day2(range);
        Long result = day2.call();

        Assertions.assertEquals(33L, result);
    }

    @Test
    void testDay2SingleInputRange6() {
        String range = "2121212118-2121212124"; // 33
        Day2 day2 = new Day2(range);
        Long result = day2.call();

        Assertions.assertEquals(0L, result);
    }

    @Test
    void testDay2SingleInputRange7() {
        String range = "585093-612147"; // 16162146
        Day2 day2 = new Day2(range);
        Long result = day2.call();

        Assertions.assertEquals(16162146L, result);
    }

    @Test
    void testDay2SingleInputRange8() {
        String range = "521461-548256"; // 14432418
        Day2 day2 = new Day2(range);
        Long result = day2.call();

        Assertions.assertEquals(14432418L, result);
    }
}