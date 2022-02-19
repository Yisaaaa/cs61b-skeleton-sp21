package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int size = 1000;
        AList<Integer> n = new AList<>();
        AList<Double> t = new AList<>();
        AList<Integer> ops = new AList<>();

        while (size <= 10000000) {
            n.addLast(size);
            ops.addLast(10000);
            timeGetLast(size, t);
            size *= 2;
        }
        printTimingTable(n, t, ops);
    }

    private static void timeGetLast(int size, AList<Double> t) {
        SLList<Integer> a = new SLList<>();
        int start = 1;
        while (start <= size) {
            a.addLast(start);
            start ++;
        }
        Stopwatch sw = new Stopwatch();
        a.getLast();
        double sec = sw.elapsedTime();
        t.addLast(sec);
    }
}
