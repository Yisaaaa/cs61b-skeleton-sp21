/** Class that prints the Collatz sequence starting from a given number.
 *  @author yisa
 */
public class Collatz {
    public static int nextNum(int n) {
        if (n == 1)
            return 1;
        else if (n % 2 != 0)
            return n * 3 + 1;
        else
            return n / 2;
    }

    public static void main(String[] args) {
        int n = 5;
        while (n != 1) {
            System.out.print(n + " ");
            n = nextNum(n);
        }
    }
}