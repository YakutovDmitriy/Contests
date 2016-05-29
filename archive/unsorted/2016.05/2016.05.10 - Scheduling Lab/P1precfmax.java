package code;

import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;

import java.math.BigInteger;
import java.util.*;

public class P1precfmax {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] p = in.nextArrayInt(n);
        List<BigInteger>[] poly = new List[n];
        for (int i = 0; i < n; i++) {
            poly[i] = new ArrayList<>();
            int size = in.nextInt() + 1;
            for (int j = 0; j < size; j++) {
                poly[i].add(BigInteger.valueOf(in.nextInt()));
            }
            Collections.reverse(poly[i]);
        }
        Set<Integer>[] g = new Set[n];
        for (int i = 0; i < n; i++) {
            g[i] = new HashSet<>();
        }
        int edges = in.nextInt();
        for (int i = 0; i < edges; i++) {
            g[in.nextInt() - 1].add(in.nextInt() - 1);
        }
        int pS = 0;
        for (int i = 0; i < n; i++) {
            pS += p[i];
        }
        BigInteger fmax = BigInteger.ZERO;
        boolean[] used = new boolean[n];
        int[] ans = new int[n];
        for (int it = 0; it < n; ++it) {
            int job = -1;
            BigInteger cur = null;
            for (int i = 0; i < n; ++i) {
                if (!used[i] && g[i].isEmpty()) {
                    BigInteger now = eval(poly[i], pS);
                    if (job < 0 || cur.compareTo(now) > 0) {
                        job = i;
                        cur = now;
                    }
                }
            }
            assert(job >= 0);
            fmax = fmax.max(cur);
            used[job] = true;
            pS -= p[job];
            ans[job] = pS;
            for (int i = 0; i < n; ++i) {
                g[i].remove(job);
            }
        }
        out.println(fmax);
        out.printArray(ans);
    }

    private BigInteger eval(List<BigInteger> poly, int point) {
        BigInteger bipoint = BigInteger.valueOf(point);
        BigInteger d = BigInteger.ONE;
        BigInteger ret = BigInteger.ZERO;
        for (BigInteger coef : poly) {
            ret = ret.add(d.multiply(coef));
            d = d.multiply(bipoint);
        }
        return ret;
    }
}
