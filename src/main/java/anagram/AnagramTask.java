package anagram;

import java.util.*;

public class AnagramTask {
    public static boolean isAnagramOnMap(String s1, String s2) {
        Map<Character, Integer> s1map = getCharacterIntegerMap(s1);
        Map<Character, Integer> s2map = getCharacterIntegerMap(s2);

        for (Map.Entry<Character, Integer> itm : s1map.entrySet()) {
            s2map.remove(itm.getKey(), itm.getValue());
        }
        return s2map.size() == 0;
    }

    private static Map<Character, Integer> getCharacterIntegerMap(String string) {
        char[] chars = string.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char aChar : chars) {
            map.computeIfPresent(aChar, (k, v) -> v + 1);
            map.putIfAbsent(aChar, 1);
        }
        return map;
    }

    public static boolean isAnagramOnTwoEmbeddedCycles(String s1, String s2) {
        char[] s1chars = s1.toCharArray();
        char[] s2chars = s2.toCharArray();

        boolean charsEqual;
        for (char s1char : s1chars) {
            charsEqual = false;
            for (int j = 0; j < s2chars.length; j++) {
                if (s1char == s2chars[j]) {
                    charsEqual = true;
                }
                if (j == s2chars.length - 1 && !charsEqual) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isAnagramOnTwoSortedArrays(String s1, String s2) {
        List<Character> s1charsList = new ArrayList<>(s1.chars()
                                                    .mapToObj(e -> (char) e)
                                                    .toList());

        List<Character> s2charsList = new ArrayList<>(s2.chars()
                                                    .mapToObj(e -> (char) e)
                                                    .toList());

        s1charsList.sort(Comparator.naturalOrder());
        s2charsList.sort(Comparator.naturalOrder());

        return s1charsList.equals(s2charsList);
    }
}
