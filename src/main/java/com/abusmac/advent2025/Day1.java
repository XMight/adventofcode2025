package com.abusmac.advent2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

public class Day1 implements Callable<Integer> {
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
            String string = strings[0];
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
}
