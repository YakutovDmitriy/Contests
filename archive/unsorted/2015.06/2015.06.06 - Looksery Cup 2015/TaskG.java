package code;

import util.FastReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskG {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + in.nextInt();
        }
        Arrays.sort(a);
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i - 1]) {
                out.println(":(");
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(" ");
            int value = a[i] - i;
            out.print(value);
        }
        out.println();
    }
}
