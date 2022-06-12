package deque;

import org.junit.Test;

import java.security.Principal;

import static org.junit.Assert.*;

public class ArrayDequeTest{

    @Test
    public void addFirstAndSizeTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(12);
        assertEquals(-1, a.nextFront);
        a.addFirst(4);
        assertEquals(6, a.nextFront);

        for (int i = 1; i <= 6; i ++) {
            a.addFirst(i);
        }
        assertEquals(0, a.nextFront);
        assertEquals(true, a.isFull());
        a.addFirst(123);
        assertEquals(123, (int) a.get(0));
        a.addFirst(234);
        assertEquals(234, (int) a.get(0));

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

        assertEquals("12 23 2 45 4 7 11 1 17", a.stringArrayDeque());
        assertEquals(16, a.length());

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
        assertEquals("12 23 2 45 4 7 11 1 17", b.stringArrayDeque());
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> a;
        ArrayDeque<Integer> b;
        a = genNaturals(6);
        b = genNaturals(6);
        assertTrue(a.equals(b));

        b.removeFirst();
        b.addLast(345);
        assertFalse(a.equals(b));

        a = genNaturals(2);
        ArrayDeque<String> c = new ArrayDeque<>("hello");
        c.addFirst("hey");

        assertFalse(a.equals(c));
    }

    public static ArrayDeque<Integer> genNaturals(int length) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        int counter = 1;
        while (counter <= length) {
            a.addFirst(counter);
            counter++;
            a.addLast(counter);
            counter++;
        }
        return a;
    }

}
