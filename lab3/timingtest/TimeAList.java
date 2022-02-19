package timingtest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        int size = 1000;
        AList<Integer> n = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> ops = new AList<>();

        while (size <= 64000) {
            timeAListConstruction(size, n, times, ops);
            size *= 2;
        }

        printTimingTable(n, times, ops);
    }

    public static void timeAListConstruction(int size, AList<Integer> n, AList<Double> times, AList<Integer> ops) {
        Stopwatch sw = new Stopwatch();
        AList<Integer> a = new AList<>();
        int start = 1;
        while (start <= size) {
            a.addLast(start);
            start ++;
        }
        double sec = sw.elapsedTime();
        n.addLast(size);
        times.addLast(sec);
        ops.addLast(size);
    }
}
