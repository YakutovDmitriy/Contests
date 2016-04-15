package code;

import util.FastReader;
import util.FastWriter;
import util.math.MathUtils;

import java.util.Arrays;

public class Sequences {
    private static final int MOD = 999_999_937;

    public void solve(int testNumber, FastReader in, FastWriter out) {
        int[][] go = new int[5][5];
        for (int i = 0; i < go.length; i++) {
            Arrays.fill(go[i], 1);
        }
        go[3][2] = go[4][2] = go[3][4] = go[4][4] = 0;
        while (true) {
            long n = in.nextLong();
            if (n == 0) {
                break;
            }
            int[][] res = MathUtils.pow(go, n, MOD);
            int ans = 0;
            for (int i = 0; i < res.length; i++) {
                ans = (ans + res[i][0]) % MOD;
            }
            out.println(ans);
        }
    }
}
