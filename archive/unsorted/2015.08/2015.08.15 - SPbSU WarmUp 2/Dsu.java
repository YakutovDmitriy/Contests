package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.DSU;

public class Dsu {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        DSU dsu = new DSU(n);
        int count = in.nextInt();
        long ans = 0;
        final int MAX = 1_000_000_000;
        for (int it = 0; it < count; it++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int c = in.nextInt();
            int du = (in.nextInt() % n + n) % n;
            int dv = (in.nextInt() % n + n) % n;
            int dc = (in.nextInt() % MAX + MAX) % MAX;
            int m = in.nextInt();
            for (int i = 0; i < m; i++) {
                if (dsu.unite(u, v)) {
                    ans += c;
                }
                u += du;
                if (u >= n) u -= n;
                v += dv;
                if (v >= n) v -= n;
                c += dc;
                if (c >= MAX) c -= MAX;
            }
        }
        out.println(ans);
    }
}
