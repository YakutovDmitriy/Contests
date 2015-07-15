package code;



import util.FastReader;
import java.io.PrintWriter;
import java.util.Locale;

public class TaskB {

    public static final Locale LOCALE = new Locale("US");

    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][] len = new int[n][2 * n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 2 * i + 2; ++j) {
                len[i][j] = in.nextInt();
            }
        }
        double[][] dp = new double[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                int l1 = len[i][j * 2];
                int l2 = len[i][j * 2 + 1];
                if (l1 < l2) {
                    dp[i][j] = l1 + dp[i + 1][j];
                } else if (l1 > l2) {
                    dp[i][j] = l2 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = (dp[i + 1][j + 1] + l2 + dp[i + 1][j] + l1) / 2;
                }
            }
        }
        out.printf(LOCALE, "%.15f\n", dp[0][0]);
    }
}
