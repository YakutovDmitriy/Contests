package code;

import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;
import util.dataStructures.DSU;
import util.dataStructures.PairIntInt;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        PairIntInt[] a = new PairIntInt[n];
        for (int i = 0; i < n; i++) {
            a[i] = new PairIntInt(in.nextInt(), i);
        }
        ArrayUtils.sort(a);
        ArrayUtils.reverse(a);
        int[] ans = new int[n + 1];
        DSU dsu = new DSU(n);
        boolean[] used = new boolean[n];
        for (PairIntInt p : a) {
            int i = p.second;
            int x = p.first;
            used[i] = true;
            if (i > 0 && used[i - 1]) {
                dsu.unite(i - 1, i);
            }
            if (i < n - 1 && used[i + 1]) {
                dsu.unite(i, i + 1);
            }
            int size = dsu.getSizeOf(i);
            ans[size] = Math.max(ans[size], x);
        }
        for (int i = n - 1; i > 0; i--) {
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }
        out.printArray(Arrays.copyOfRange(ans, 1, ans.length));
    }
}
