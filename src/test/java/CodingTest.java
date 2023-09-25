import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
