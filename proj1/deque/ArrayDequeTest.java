package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest{

    @Test
    public void addFirstAndSizeTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(12);
        assertEquals(7, a.nextFirst);
        assertEquals(1, a.size());
        assertEquals((Integer) 12, a.get(0));

        a.addFirst(34);
        assertEquals((Integer) 34, a.get(7));
        assertEquals(2, a.size());
        assertEquals(6, a.nextFirst);

        // second Test

    }

    @Test
    public void addLastAndAddFirstTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        ArrayDeque<Integer> b = new ArrayDeque<>();
        int i = 1;
        while (a.size() < 8) {
            a.addLast(i);
            b.addFirst(i);
            i *= 2;
        }
        assertEquals("128 1 2 4 8 16 32 64", a.stringArrayDeque());
        assertEquals("1 128 64 32 16 8 4 2", b.stringArrayDeque());
    }

    public static void main(String[] args) {
        ArrayDeque<String> a = new ArrayDeque<>();
        System.out.println(a.get(3) == null);
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 1; i <= 8; i++) {
            a.addFirst(i);
        }

        assertEquals(0, a.nextFirst);
        assertEquals(8, (int) a.removeFirst());
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        assertEquals(5, a.nextFirst);
        a.removeFirst();
        a.removeFirst();
        assertEquals(7, a.nextFirst);
        assertEquals("1 ", a.stringArrayDeque());
        a.removeFirst();
        assertNull(a.removeFirst());
    }

    @Test
    public void removeLastTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 1; i <= 8; i++) {
            a.addLast(i);
        }

        assertEquals(1, a.nextLast);
    }
}
