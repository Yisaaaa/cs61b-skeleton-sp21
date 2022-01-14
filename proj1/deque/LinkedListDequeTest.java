package deque;

import jh61b.junit.In;
import org.junit.Test;

import java.util.spi.LocaleNameProvider;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        /*
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
		*/
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        /*
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
        */
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        /*
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
        */
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        /*
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
        */
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        /*
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

        */
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        /*
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

        */
    }

    // My Tests
    @Test
    public void emptyLinkedListDequeTest(){
        LinkedListDeque<Integer> myList = new LinkedListDeque<>();

        LinkedListDeque.IntNode[] expectedValues =  {myList.sentinel, myList.sentinel};
        LinkedListDeque.IntNode[] result = {myList.sentinel.prev, myList.sentinel.next};

        assertArrayEquals(expectedValues, result);
    }

    @Test
    public void LinkedListDequeTest() {
        LinkedListDeque<String> myList = new LinkedListDeque<>("hello");
        LinkedListDeque<String>.IntNode theNode = myList.sentinel.next;

        LinkedListDeque.IntNode[] expectedValues =  {theNode, theNode, myList.sentinel, myList.sentinel};
        LinkedListDeque.IntNode[] result = {myList.sentinel.next, myList.sentinel.prev, myList.sentinel.next.prev, myList.sentinel.next.next};

        assertArrayEquals(expectedValues, result);
    }

    @Test
    public void addFirstTest() {
        LinkedListDeque<Integer> myList = new LinkedListDeque<>(69);
        LinkedListDeque.IntNode veryFirst = myList.sentinel.next;
        myList.addFirst(96);
        int result = myList.sentinel.next.item;
        assertEquals(96, result);

        myList.addFirst(56);
        myList.addFirst(72);
        assertEquals(myList.sentinel.prev, veryFirst);
    }

    @Test
    public void addLastTest() {
        LinkedListDeque<Integer> empty = new LinkedListDeque<>();
        LinkedListDeque<Integer> myList = new LinkedListDeque<>(69);

        LinkedListDeque.IntNode veryFirst = myList.sentinel.next;
        myList.addLast(96);
        int result = myList.sentinel.prev.item;
        assertEquals(96, result);

        myList.addLast(72);
        result = myList.sentinel.prev.item;
        assertEquals(72, result);

        // Test empty deque
        empty.addLast(12);
        empty.addLast(34);
        int[] expected = {12, 34};
        int[] results = {empty.sentinel.next.item, empty.sentinel.prev.item};
    }

    @Test
    public void isEmptyTest() {
        LinkedListDeque<Integer> empty = new LinkedListDeque<>();
        LinkedListDeque<Integer> myList = new LinkedListDeque<>(69);
        
        LinkedListDeque.IntNode veryFirst = myList.sentinel.next;
        assertTrue(empty.isEmpty());
        assertFalse(myList.isEmpty());
    }

    @Test
    public void printDequeTest() {
        LinkedListDeque<Integer> deque = generateNautralDeque(4);
        String expected = "1 2 3 4";
        String result = deque.printDequeHelper();
        assertEquals(expected, result);

        LinkedListDeque<Integer> dequeLonger = generateNautralDeque(13);
        expected = "1 2 3 4 5 6 7 8 9 10 11 12 13";
        result = dequeLonger.printDequeHelper();
        assertEquals(expected, result);
    }

    @Test
    public void removeFirstTest() {
        LinkedListDeque<Integer> deque = generateNautralDeque(5);
        LinkedListDeque.IntNode nextAfterRemoved = deque.sentinel.next.next;
        int result = deque.removeFirst();
        int expected = 1;
        assertEquals(expected, result);
        assertEquals(deque.sentinel.next, nextAfterRemoved);

        nextAfterRemoved = deque.sentinel.next.next;
        result = deque.removeFirst();
        expected = 2;
        assertEquals(expected, result);
        assertEquals(deque.sentinel.next, nextAfterRemoved);

        // Test empty deque
        LinkedListDeque<Integer> empty = new LinkedListDeque<>();
        assertEquals(null, empty.removeFirst());
    }

    @Test
    public void removeLastTest() {
        LinkedListDeque<Integer> deque = generateNautralDeque(5);
        LinkedListDeque.IntNode lastAfterRemoved = deque.sentinel.prev.prev;
        int result = deque.removeLast();
        int expected = 5;
        assertEquals(expected, result);
        assertEquals(deque.sentinel.prev, lastAfterRemoved);

        // Test empty deque
        LinkedListDeque<Integer> empty = new LinkedListDeque<>();
        assertEquals(null, empty.removeLast());
    }

    public static LinkedListDeque<Integer> generateNautralDeque(int length) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        while (length > 0) {
            deque.addFirst(length);
            length --;
        }
        return deque;
    }
}
