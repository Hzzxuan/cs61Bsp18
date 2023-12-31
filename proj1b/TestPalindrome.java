import org.junit.Test;
import static org.junit.Assert.*;import org.junit.Test;
import static org.junit.Assert.*;import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class. */

    @Test
    public void testisPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("noon"));
    }
    @Test
    public void testisPalindromeRec(){
        assertFalse(palindrome.isPalindromeRec("cat"));
        assertTrue(palindrome.isPalindromeRec("noon"));
    }

    @Test
    public void testisPalindromeoffByOne(){
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("cat",cc));
        assertTrue(palindrome.isPalindrome("flake",cc));
    }
}
