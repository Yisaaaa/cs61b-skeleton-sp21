

package deque;


import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;



public class ArrayDequeTest {

    @Test
    public void addFirstRemoveLastIsEmptyTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int N = 32;
        for (int i = 0; i < N; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < N; i++) {
            assertEquals(i, (int) deque.removeLast());
        }

        assertTrue(deque.isEmpty());
    }

    @Test
    public void addLastRemoveFirstIsEmptyTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int N = 32;
        for (int i = 0; i < N; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < N; i++) {
            int bacon = deque.removeFirst();
            assertEquals(i, bacon);
        }

        assertTrue(deque.isEmpty());
    }

}
