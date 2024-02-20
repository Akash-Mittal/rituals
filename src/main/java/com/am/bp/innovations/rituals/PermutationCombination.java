package com.am.bp.innovations.rituals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PermutationCombination {

    public static void main(String[] args) throws IOException {
        String fileData[] = RITUALS.loadFileAndReadAsStrings("E:\\GIT_CLONE\\rituals\\ritualType1.txt").split(";");
        // RITUALS.write("rituals.txt", allpermutations);
        List<List<String>> combinations = generateUniqueCombinations(fileData, fileData.length - 1);
        for (List<String> combination : combinations) {
            System.out.println(combination);
        }

    }

    public static List<List<String>> generateUniqueCombinations(String[] arr, int k) {
        List<List<String>> result = new ArrayList<>();
        int n = arr.length;
        if (k <= n) {
            for (int i = 1; i < (1 << n); i++) {
                List<String> combination = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        combination.add(arr[j]);
                    }
                }
                if (combination.size() == k && isUniqueCombination(result, combination)) {
                    result.add(combination);
                }
            }
        }
        return result;
    }

    private static boolean isUniqueCombination(List<List<String>> combinations, List<String> combination) {
        for (List<String> c : combinations) {
            if (c.size() == combination.size() && c.containsAll(combination)) {
                return false;
            }
        }
        return true;
    }
}