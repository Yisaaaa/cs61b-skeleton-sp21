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
    }
    
}
