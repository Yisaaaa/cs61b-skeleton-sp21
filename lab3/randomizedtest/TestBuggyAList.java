package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> aListNoResizing = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        aListNoResizing.addLast(1);
        buggyAList.addLast(1);
        aListNoResizing.addLast(23);
        buggyAList.addLast(23);
        aListNoResizing.addLast(54);
        buggyAList.addLast(54);

        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> LL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                LL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = LL.size();

                assertEquals(size, size2);
            } else if (L.size() > 0) {
                // getLast
                if (operationNumber == 2) {
                    int last = L.getLast();
                    int last2 = LL.getLast();

                    assertEquals(last, last2);
                } else if (operationNumber == 3) {
                    //removeLAst (it seems to work correctly)
                    int removedLast = L.removeLast();
                    int removedLast2 = LL.removeLast();

                    assertEquals(removedLast, removedLast2);
                }
            }


        }
    }



}