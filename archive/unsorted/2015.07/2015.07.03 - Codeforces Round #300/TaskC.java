package code;

import util.FastReader;
import util.FastWriter;

public class TaskC {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] d = new int[m + 2];
        int[] h = new int[m + 2];
        for (int i = 1; i <= m; i++) {
            d[i] = in.nextInt();
            h[i] = in.nextInt();
        }
        d[0] = 1;
        h[0] = h[1] + d[1] - 1;
        d[m + 1] = n;
        h[m + 1] = h[m] + n - d[m];
        int ans = 0;
        for (int i = 0; i < m + 1; i++) {
            int j = i + 1;
            if (Math.abs(h[j] - h[i]) > d[j] - d[i]) {
                out.println("IMPOSSIBLE");
                return;
            }
            int days = d[j] - d[i] - Math.abs(h[j] - h[i]);
            ans = Math.max(ans, days / 2 + Math.max(h[i], h[j]));
        }
        out.println(ans);
    }
}
