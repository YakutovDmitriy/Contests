package code;

import util.FastReader;
import util.FastWriter;
import util.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maxim {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int p = 2;
        while (n % p != 0) {
            p++;
        }
        for (int x : solve(p)) {
            out.println(n / p * x);
        }
    }

    private List<Integer> solve(int n) {
        int[] primes = MathUtils.getPrimesTo(620);
        int cnt = primes.length;
        int[][] take = new int[cnt + 1][n + 1];
        double[] dp = new double[n + 1];
        dp[0] = -10;
        for (int p = 1; p <= cnt; p++) {
            int prime = primes[p - 1];
            double log = Math.log(prime);
            double[] newDp = dp.clone();
            long pdLong = prime;
            for (int deg = 1; pdLong <= n && deg <= 6; deg++) {
                double logPd = deg * log;
                int pd = (int) pdLong;
                if (pd < n && newDp[pd] < logPd) {
                    newDp[pd] = logPd;
                    take[p][pd] = pd;
                }
                for (int was = 0; was + pd <= n; was++) {
                    double logLcm = dp[was] + logPd;
                    if (dp[was] > -1 && newDp[was + pd] < logLcm) {
                        newDp[was + pd] = logLcm;
                        take[p][was + pd] = pd;
                    }
                }
                pdLong *= prime;
            }
            dp = newDp;
        }
        int number = n;
        List<Integer> ret = new ArrayList<>();
        for (int i = cnt; i > 0; i--) {
            int pd = take[i][number];
            if (pd > 1) {
                ret.add(pd);
                number -= pd;
            }
        }
        while (number-- > 0) {
            ret.add(1);
        }
        return ret;
    }
}
