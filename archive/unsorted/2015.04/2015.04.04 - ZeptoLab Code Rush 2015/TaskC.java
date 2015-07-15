package code;

import util.FastReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        long c = in.nextInt();
        long h1 = in.nextInt();
        long h2 = in.nextInt();
        long w1 = in.nextInt();
        long w2 = in.nextInt();
        if (w1 > w2) {
            long t = w1;
            w1 = w2;
            w2 = t;
            t = h1;
            h1 = h2;
            h2 = t;
        }
        long ans = 0;
        if (w2 > 10000) {
            for (long c2 = 0; c2 * w2 <= c; ++c2) {
                long c1 = (c - c2 * w2) / w1;
                ans = Math.max(ans, c1 * h1 + c2 * h2);
            }
        }
        long s = c / w2;
        for (long c2 = s; c2 > s - 300000 && c2 > 0; --c2) {
            long c1 = (c - c2 * w2) / w1;
            ans = Math.max(ans, c1 * h1 + c2 * h2);
        }
        s = c / w1;
        for (long c1 = s; c1 > s - 300000 && c1 > 0; --c1) {
            long c2 = (c - c1 * w1) / w2;
            ans = Math.max(ans, c1 * h1 + c2 * h2);
        }
        out.println(ans);
    }
}
