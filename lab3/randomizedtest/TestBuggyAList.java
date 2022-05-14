package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {

        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        for (int i = 0; i <= 2; i++) {
            a.addLast(i);
            b.addLast(i);
        }

        for (int i = 0; i <= 2; i++) {
            assertEquals(a.removeLast(), b.removeLast());
        }


    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            } else if (L.size() > 0) {
                if (operationNumber == 2) {
                    int last = L.getLast();
                    System.out.println("Last: " + last);
                } else if (operationNumber == 3) {
                    int removedLast = L.removeLast();
                    System.out.println("Removed Last: " + removedLast);
                }
            }
        }
    }

}
