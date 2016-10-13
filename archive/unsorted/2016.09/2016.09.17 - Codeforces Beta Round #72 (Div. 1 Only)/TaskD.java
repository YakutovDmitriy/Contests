package code;

import util.FastReader;
import util.FastWriter;
import util.math.MathUtils;

public class TaskD {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        if (!MathUtils.isPrime(k)) {
            out.println(0);
        } else {
            out.println(solve(b, k) - solve(a - 1, k));
        }
    }

    private int solve(int n, int k) {
        if (n < k) {
            return 0;
        }
        if ((long) k * k > n) {
            return 1;
        }
        int[] smallPrimes = MathUtils.getPrimesTo(k - 1);
        int ret = 0;
        int d = n / k;
        if (smallPrimes.length < 20) {
            for (int mask = 0; mask < 1 << smallPrimes.length; mask++) {
                long prod = 1;
                for (int i = 0; i < smallPrimes.length; i++) {
                    if (((mask >> i) & 1) == 1) {
                        prod *= smallPrimes[i];
                    }
                }
                int cur = (int) (d / prod);
                if (Integer.bitCount(mask) % 2 == 1) {
                    cur *= -1;
                }
                ret += cur;
            }
        } else {
            boolean[] foo = new boolean[d + 1];
            for (int p : smallPrimes) {
                for (int x = p; x < foo.length; x += p) {
                    foo[x] = true;
                }
            }
            for (int i = 1; i < foo.length; i++) {
                if (!foo[i]) {
                    ret++;
                }
            }
        }
        return ret;
    }
}
