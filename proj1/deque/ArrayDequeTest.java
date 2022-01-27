package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest{

    @Test
    public void addFirstAndSizeTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(12);
        assertEquals(-1, a.nextFirst);
        a.addFirst(4);
        assertEquals(6, a.nextFirst);

        for (int i = 1; i <= 6; i ++) {
            a.addFirst(i);
        }
        assertEquals(0, a.nextFirst);
        assertEquals(true, a.isFull());
        a.addFirst(123);
        assertEquals(12, (int) a.get(0));
        a.addFirst(234);

    }

    @Test
    public void addFirstAndAddLastTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(12);
        a.addLast(23);
        a.addFirst(17);
        a.addLast(2);
        a.addLast(45);
        a.addFirst(1);
        a.addLast(4);
        a.addLast(7);
        a.addFirst(11);

        assertEquals("12 23 2 45 4 7 1 17", a.stringArrayDeque());

        ArrayDeque<Integer> b = new ArrayDeque<>();
        b.addLast(12);
        b.addLast(23);
        b.addFirst(17);
        b.addLast(2);
        b.addLast(45);
        b.addFirst(1);
        b.addLast(4);
        b.addLast(7);
        b.addFirst(11);
        assertEquals("12 23 2 45 4 7 1 17", b.stringArrayDeque());
    }


/**
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
        a.removeLast();
        a.removeLast();
        assertEquals(7, a.nextLast);

        ArrayDeque<Integer> b = new ArrayDeque<>();
        assertNull(b.removeLast());
    }
    */

}
