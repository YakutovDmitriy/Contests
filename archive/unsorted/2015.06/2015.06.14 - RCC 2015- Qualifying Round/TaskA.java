package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskA {
    public static final int MOD = 1_000_000_007;
    public static final int T = 110;
    public static int[][] c = new int[T][T];

    static {
        for (int i = 0; i < T; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
                if (c[i][j] >= MOD) c[i][j] -= MOD;
            }
        }
    }

    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        char[] s = in.nextCharArray();
        int cnt1 = 0;
        int cnt0 = 0;
        for (int i = 0; i < n; ++i) {
            cnt1 += s[i] == '1' ? 1 : 0;
            cnt0 += s[i] == '0' ? 1 : 0;
        }
        int ans = 0;
        for (int one = cnt1; one >= 0; one -= 3) {
            int zero = k - one;
            if (zero < 0 || zero % 2 != cnt0 % 2 || zero > cnt0) continue;
            ans += c[k][one];
            if (ans >= MOD) ans -= MOD;
        }
        out.println(ans);
    }
}
