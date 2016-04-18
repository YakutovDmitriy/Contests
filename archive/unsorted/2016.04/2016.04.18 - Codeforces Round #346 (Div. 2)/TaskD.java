package code;

import util.FastReader;
import util.FastWriter;

public class TaskD {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = (i + 1) % n;
            int k = (i + 2) % n;
            int turn = Integer.signum((x[j] - x[i]) * (y[k] - y[j]) - (y[j] - y[i]) * (x[k] - x[j]));
            if (turn > 0) {
                ans++;
            }
        }
        out.println(ans);
    }
}
