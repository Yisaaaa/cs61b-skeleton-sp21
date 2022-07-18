package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import jh61b.junit.In;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    public String printFailureSequence(ArrayDequeSolution<String> sequence) {
        StringBuilder sequenceString = new StringBuilder();

        for (String operation : sequence) {
            sequenceString.append(operation + "\n");
        }

        return sequenceString.toString();
    }


    @Test
    public void basicRandomizedTest() {
        /* A test for the four basic operations -- adds and removes */

        int N = 100;
        String failureSequenceString;
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution();
        ArrayDequeSolution<String> failureSequence = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> buggy = new StudentArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int randomOperation = StdRandom.uniform(0, 4);

            if (randomOperation == 0) {
                solution.addFirst(i);
                buggy.addFirst(i);
                failureSequence.addLast("addFirst(" + i + ")");
            } else if (randomOperation == 1) {
                solution.addLast(i);
                buggy.addLast(i);
                failureSequence.addLast("addLast(" + i + ")");
            }

            if (solution.size() > 0) {
                if (randomOperation == 2) {
                    Integer removeFromSolution = solution.removeFirst();
                    Integer removeFromBuggy = buggy.removeFirst();

                    failureSequence.addLast("removeFirst()");
                    //failureSequenceString = printFailureSequence(failureSequence);
                    if (!(removeFromSolution == null && removeFromBuggy == null)) {
                        assertEquals(printFailureSequence(failureSequence), removeFromSolution, removeFromBuggy);
                    }
                } else if (randomOperation == 3) {
                    Integer removeFromSolution = solution.removeLast();
                    Integer removeFromBuggy = buggy.removeLast();

                    failureSequence.addLast("removeLast()");
                    //failureSequenceString = printFailureSequence(failureSequence);
                    if (!(removeFromSolution == null && removeFromBuggy == null)) {
                        assertEquals(printFailureSequence(failureSequence), removeFromSolution, removeFromBuggy);
                    }
                }
            }
        }
    }

}
