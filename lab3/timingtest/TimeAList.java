package timingtest;
import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.zip.DeflaterOutputStream;


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
        int N = 1000;
        int LIMIT = 128000;
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        // while N < limit
        while (N <= LIMIT) {
            AList<Integer> a = new AList<>();
            Stopwatch sw = new Stopwatch();

            for (int i = 1; i <= N; i++) {
                a.addLast(i);
            }
            double timeInSeconds = sw.elapsedTime();
            Ns.addLast(N);
            opCounts.addLast(N);
            times.addLast(timeInSeconds);

            N *= 2;
        }

        // print table
        printTimingTable(Ns, times, opCounts);

    }
}
