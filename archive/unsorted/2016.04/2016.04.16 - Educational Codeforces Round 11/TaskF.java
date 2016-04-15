package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.ConvexHullTrick;

public class TaskF {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] a = in.nextArrayInt(n);
        long[] s = new long[n + 1];
        long[] p = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + a[i - 1];
            p[i] = p[i - 1] + (long) a[i - 1] * i;
        }
        ConvexHullTrick cht = new ConvexHullTrick(n, (long) 3e12);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            cht.addLine(i, i * s[i] - p[i]);
            ans = Math.max(ans, p[i + 1] + cht.evaluate(-s[i + 1]));
        }
        out.println(ans);
    }
}
