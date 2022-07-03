package deque;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;



public class ArrayDequeTest {


    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        AD.addFirst(234);

        assertEquals("{234}", AD.toString());
        assertFalse("Deque should not be empty", AD.isEmpty());

        int actual = AD.removeLast();
        assertEquals("Should be equal to 234", 234, actual);
        assertTrue("Deque should be empty", AD.isEmpty());
    }



    @Test
    public void addUntilFullTest() {
        ArrayDeque<Integer> AD = ArrayDeque.of(1,2,3,4,5,6,7,8,9,0);

        assertTrue(AD.isFull());
        assertEquals(8, AD.size());
    }



    @Test
    public void removeEmptyTest() {
            ArrayDeque<Integer> AD = ArrayDeque.of(4, 1, 5);
            AD.removeFirst();
            AD.removeLast();
            AD.removeFirst();
            AD.removeFirst();

        assertEquals("Size should be equal to 0", 0, AD.size());
    }



    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        LinkedListDeque<Integer> LD = new LinkedListDeque<>();
        int N = 500;

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3); // 0, 1, 2

        }
    }



    @Test
    public void resizeFrontWentAroundTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(6);
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);

        deque.resizeHelper(deque.getLength() * 2);

        assertEquals(6, deque.size);
        assertEquals(16, deque.getLength());
        String expected = "{1, 2, 3, 4, 5, 6}";
        assertEquals(expected, deque.toString());
    }



    @Test
    public void resizeBackWentAroundTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);

        deque.resizeHelper(deque.getLength() * 2);

        String expected = "{1, 2, 3, 4, 5, 6}";
        assertEquals(expected, deque.toString());
    }

    @Test
    public void resizeNoWentAround(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);

        deque.resizeHelper(deque.getLength() * 2);

        assertEquals("{1, 2, 3, 4, 5, 6, 7, 8}", deque.toString());
        assertEquals(8, deque.size);
        assertEquals(16, deque.getLength());

        deque.resizeHelper(4);
        assertEquals(16, deque.getLength());
    }

}
