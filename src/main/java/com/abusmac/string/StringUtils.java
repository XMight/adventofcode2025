package com.abusmac.string;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static List<String> splitIntoSameLengthNumbers(String s, int len, int splitLen) {
        List<String> result = new ArrayList<>();

        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i += splitLen) {
            String number = "";
            for (int k = 0; k < splitLen && k + i < len; k++) {
                number += charArray[i + k];
            }

            result.add(number);
        }

        return result;
    }
}
