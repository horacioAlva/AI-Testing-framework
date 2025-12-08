package com.epam.rd.autotasks.words;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        if (sample == null || words == null || words.length == 0) {
            return 0;
        }

        int count = 0;
        String cleanSample = sample.strip().toLowerCase();

        for (String word : words) {
            if (word.strip().toLowerCase().equals(cleanSample)) {
                count++;
            }
        }
        return count;
    }

    public static String[] splitWords(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }


        String[] parts = text.split("[,.;:?! ]+");

        List<String> result = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.add(part);
            }
        }

        if (result.isEmpty()) {
            return null;
        }

        return result.toArray(new String[0]);
    }

    public static String convertPath(String path, boolean toWin) {
        if (path == null || path.isEmpty()) {
            return null;
        }

        boolean isUnix = path.contains("/");
        boolean isWin = path.contains("\\");

        if (isUnix && isWin) {
            return null;
        }

        if (path.indexOf('~') != path.lastIndexOf('~')) return null;
        if (path.contains("~") && !path.startsWith("~")) return null;
        if (path.contains("~") && isWin) return null;

        if (path.indexOf("C:") != path.lastIndexOf("C:")) return null;
        if (path.contains("C:") && !path.startsWith("C:")) return null;
        if (path.contains("C:") && isUnix) return null;

        if (toWin && isWin) return path;
        if (!toWin && isUnix) return path;

        // --- UNIX → WINDOWS ---
        if (toWin) {
            String result = path;

            if (path.startsWith("~")) {
                result = "C:\\User" + path.substring(1);
            } else if (path.startsWith("/")) {
                result = "C:" + path;
            }

            result = result.replace("/", "\\");
            return result;
        }

        // --- WINDOWS → UNIX ---
        String result = path;

        if (path.startsWith("C:\\User")) {
            result = "~" + path.substring(7);
        } else if (path.startsWith("C:\\")) {
            result = path.substring(2);
        }

        result = result.replace("\\", "/");
        return result;
    }

    public static String joinWords(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }

        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (String word : words) {
            if (!word.isEmpty()) {
                joiner.add(word);
            }
        }

        String result = joiner.toString();
        return result.equals("[]") ? null : result;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}