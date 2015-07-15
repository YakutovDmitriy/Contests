package code;



import util.FastReader;
import java.io.PrintWriter;
import java.util.Locale;

public class TaskC {
    public static final Locale LOCALE = new Locale("US");
    public static final double EPS = 1e-8;

    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] len = new int[n];
        for (int i = 0; i < n; ++i) {
            len[i] = in.nextInt();
        }
        double lr = 0;
        double rr = 1e6;
        for (int it = 0; it < 50; ++it) {
            double r = (lr + rr) / 2;
            double[] dp = new double[n + 1];
            boolean ok = true;
            for (int i = 0; i < n; ++i) {
                int l = len[i];
                double mn = Math.abs(dp[i] - l);
                if (mn > r + EPS) {
                    ok = false;
                    break;
                }
                double mx = Math.min(r, dp[i] + l);
                dp[i + 1] = mx;
            }
            if (ok) {
                rr = r;
            } else {
                lr = r;
            }
        }
        out.printf(LOCALE, "%.15f\n", (lr + rr) / 2);
    }
}
