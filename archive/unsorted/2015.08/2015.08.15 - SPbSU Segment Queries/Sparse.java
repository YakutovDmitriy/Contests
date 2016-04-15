package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.segments.SparseTableMin;

public class Sparse {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        a[0] = in.nextInt();
        for (int i = 1; i < n; i++) {
            a[i] = (23 * a[i - 1] + 21563) % 16714589;
        }
        SparseTableMin table = new SparseTableMin(a);
        int u = in.nextInt();
        int v = in.nextInt();
        for (int i = 1; i < m; i++) {
            int ans = table.getMin(Math.min(u, v) - 1, Math.max(u, v));
            int newU = (17 * u + 751 + ans + 2 * i) % n + 1;
            int newV = (13 * v + 593 + ans + 5 * i) % n + 1;
            u = newU;
            v = newV;
        }
        out.println(u + " " + v + " " + table.getMin(Math.min(u, v) - 1, Math.max(u, v)));
    }
}
