import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}
