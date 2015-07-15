package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int l = in.nextInt();
        int r = in.nextInt();
        int x = in.nextInt();
        int[] c = in.nextArrayInt(n);
        int ans = 0;
        for (int mask = 0; mask < 1 << n; mask++) {
            if (Integer.bitCount(mask) < 2) continue;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    min = Math.min(min, c[i]);
                    max = Math.max(max, c[i]);
                    sum += c[i];
                }
            }
            if (max - min >= x && sum >= l && sum <= r) {
                ans++;
            }
        }
        out.println(ans);
    }
}
