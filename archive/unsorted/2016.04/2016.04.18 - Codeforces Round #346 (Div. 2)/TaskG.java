package code;

import util.FastReader;
import util.FastWriter;

public class TaskG {
    final int mod = 1_000_000_007;

    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] h = in.nextArrayInt(n);
        int answer = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            answer = add(answer, h[i] - 1);
            int x = h[i] - 1;
            if (i > 0) {
                x = Math.min(x, h[i - 1] - 1);
                answer = add(answer, mul(cur, x));
            }
            if (i < n - 1) {
                x = Math.min(x, h[i + 1] - 1);
                int y = Math.min(h[i] - 1, h[i + 1] - 1);
                cur = add(mul(cur, x), y);
            }
        }
        out.println(answer);
    }

    private int mul(int a, int b) {
        return (int) (((long) a * b) % mod);
    }

    private int add(int a, int b) {
        a += b;
        if (a >= mod) a -= mod;
        return a;
    }
}
