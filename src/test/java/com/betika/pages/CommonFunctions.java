package com.betika.pages;

import java.util.*;

public class CommonFunctions {
    public static List<Integer> randNums(int maxVal) {
        Set<Integer> set = new HashSet<>();

        Random random = new Random();

        while (set.size() < 3) {
            int num = random.nextInt(maxVal);

            if (num != 0)
                set.add(num);
        }

        return new ArrayList<>(set);
    }

    public static String stringChecker(String str) {
        if (str.endsWith("..."))
            return str.substring(0, str.length()-3).toLowerCase();

        return str.toLowerCase();
    }
}
