package code;

import util.FastReader;
import util.FastWriter;
import util.math.MathUtils;

public class TaskC {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[][] values = new int[n][];
        for (int i = 0; i < n; i++) {
            int[] primes = MathUtils.getPrimeDivisorsOf(in.nextInt());
            int len = primes.length;
            values[i] = new int[1 << len];
            for (int mask = 0; mask < values[i].length; mask++) {
                int value = 1;
                for (int j = 0; j < len; j++) {
                    if (((mask >> j) & 1) == 1) {
                        value *= primes[j];
                    }
                }
                values[i][mask] = value;
            }
        }
        int[] count = new int[500_500];
        boolean[] used = new boolean[n];
        long ans = 0;
        for (int it = 0; it < q; it++) {
            int i = in.nextInt() - 1;
            int[] v = values[i];
            if (used[i]) {
                for (int value : v) {
                    count[value]--;
                }
            }
            int good = 0;
            for (int mask = 0; mask < v.length; mask++) {
                int value = v[mask];
                int now = count[value];
                if (Integer.bitCount(mask) % 2 == 1) {
                    now *= -1;
                }
                good += now;
            }
            if (!used[i]) {
                for (int value : v) {
                    count[value]++;
                }
            }
            if (used[i]) {
                ans -= good;
                used[i] = false;
            } else {
                ans += good;
                used[i] = true;
            }
            out.println(ans);
        }
    }
}
