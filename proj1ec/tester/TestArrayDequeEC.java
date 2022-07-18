package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import jh61b.junit.In;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    public void printFailureSequence(ArrayDequeSolution<String> sequence) {

        for (String operation : sequence) {
            System.out.println(operation);
        }
    }


    @Test
    public void basicRandomizedTest() {
        /* A test for the four basic operations -- adds and removes */

        int N = 100;
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

                    assertEquals(removeFromSolution, removeFromBuggy);
                    failureSequence = new ArrayDequeSolution<>();
                } else if (randomOperation == 3) {
                    Integer removeFromSolution = solution.removeLast();
                    Integer removeFromBuggy = buggy.removeLast();

                    assertEquals(removeFromSolution, removeFromBuggy);
                    failureSequence = new ArrayDequeSolution<>();
                }
            }
        }
    }

}
