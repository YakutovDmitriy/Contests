package code;

import util.FastReader;
import util.FastWriter;

public class TaskB {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] a = in.nextArrayInt(n);
        int count = a[0];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                a[i] = (a[i] + n - count) % n;
            } else {
                a[i] = (a[i] + count) % n;
            }
        }
        boolean ans = true;
        for (int i = 0; i < n; i++) {
            ans &= a[i] == i;
        }
        out.println(ans ? "Yes" : "No");
    }
}
