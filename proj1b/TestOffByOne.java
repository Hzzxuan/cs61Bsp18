import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        char c1 = 'A';
        char c2 = 'A';
        char c3 = 'B';
        assertTrue(offByOne.equalChars(c1,c2));
        assertFalse(offByOne.equalChars(c1,c3));
    }
}
