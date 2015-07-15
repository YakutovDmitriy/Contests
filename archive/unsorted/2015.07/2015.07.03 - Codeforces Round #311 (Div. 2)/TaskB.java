package code;

import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;

import java.util.Arrays;
import java.util.Locale;

public class TaskB {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int w = in.nextInt();
        int[] a = in.nextArrayInt(2 * n);
        Arrays.sort(a);
        double ans = Math.min(w, 1.5 * n * Math.min(2 * a[0], a[n]));
        out.printf(Locale.US, "%.15f\n", ans);
    }
}
