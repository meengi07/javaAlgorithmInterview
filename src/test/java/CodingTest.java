import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class CodingTest {

    @Test
    @DisplayName("125. Valid Palindrome")
    public void validPalindrome() {
        String s = "A man, a plan, a canal: Panama";
        assertTrue(isPalindrome(s));
    }

    public boolean isPalindrome(String s) {

        int start = 0;
        int end = s.length() - 1;

        while(start < end) {
            if(!Character.isLetterOrDigit(s.charAt(start))) start++;
            else if(!Character.isLetterOrDigit(s.charAt(end))) end--;
            else {
                if(Character.toLowerCase(s.charAt(start))
                        != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }

    @Test
    @DisplayName("344. Reverse String")
    public void reverseStringTest() {
        char[] s = {'h','e','l','l','o'};
        char[] result = reverseString(s);
        assertArrayEquals(s, result);
    }

    public char[] reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;

        while(start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
        return s;
    }

    @Test
    public void reorderLogFiles() {
        String[] logs = new String[5];
        logs[0] = "dig1 8 1 5 1";
        logs[1] = "let1 art can";
        logs[2] = "dig2 3 6";
        logs[3] = "let2 own kit dig";
        logs[4] = "let3 art zero";

        List<String> textList = new ArrayList<>();
        List<String> numList = new ArrayList<>();

        for(String log : logs) {
            if(Character.isDigit(log.split(" ")[1].charAt(0))) {
                numList.add(log);
            } else {
                textList.add(log);
            }
        }
        textList.sort((s1, s2) -> {
            String[] x1 = s1.split(" ", 2);
            String[] x2 = s2.split(" ", 2);

            int compared = x1[1].compareTo(x2[1]);
            if(compared == 0) return x1[0].compareTo(x2[0]);
            else return compared;
        });

        textList.addAll(numList);
        System.out.println(textList);
    }

    @Test
    @DisplayName("819. Most Common Word")
    void mostCommonWord() {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};

        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> counts = new HashMap<>();

        String[] split = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");

        for(String word : split) {
            if(!ban.contains(word)) {
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
        }

        assertEquals("ball", Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey());
    }

    @Test
    @DisplayName("49. Group Anagrams")
    void groupAnarams() {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            if(!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(str);
        }

        assertEquals(3, map.size());
    }

    int left, maxLen;

    @Test
    @DisplayName("5. Longest Palindromic Substring")
    void longestPalindromicSubstring() {
        String s = "babad";
        String palindrome = longestPalindrome(s);
        assertEquals("bab", palindrome);
    }
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len<2) return s;

        for(int i=0; i<len-1; i++) {
            extendPalindrome(s, i, i+1);
            extendPalindrome(s, i, i+2);
        }
        return s.substring(left, left+maxLen);
    }

    public void extendPalindrome(String s, int j, int k) {
        while(j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if(maxLen < k - j - 1) {
            left = j + 1;
            maxLen = k - j - 1;
        }
    }
}
