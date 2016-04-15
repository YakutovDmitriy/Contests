package code;

import util.ArrayUtils;
import util.FastReader;
import util.dataStructures.segments.SparseTableMax;
import util.math.MathUtils;

import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskD {

    public static final int MOD = 1_000_000_007;

    SparseTableMax table;
    ArrayList<Integer>[] poses;
    int[] y;
    int[] cat;

    ArrayList<Integer> get(ArrayList<Integer> poses, int from, int to) {
        int l = -1;
        int r = poses.size();
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (poses.get(m) <= to) {
                l = m;
            } else {
                r = m;
            }
        }
        int ansR = l;
        l = -1;
        r = poses.size();
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (poses.get(m) >= from) {
                r = m;
            } else {
                l = m;
            }
        }
        int ansL = r;
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = ansL; i <= ansR; i++) {
            ret.add(poses.get(i));
        }
        return ret;
    }

    int dfs(int l, int r) {
        if (l > r) return 1;
        int max = table.getMax(l, r + 1);
        ArrayList<Integer> p = get(poses[max], l, r);

        int ans = cat[p.size()];
        ans = (int) ((long) ans * dfs(l, p.get(0) - 1) % MOD);
        ans = (int) ((long) ans * dfs(p.get(p.size() - 1) + 1, r) % MOD);
        for (int i = 0; i < p.size() - 1; i++) {
            ans = (int) ((long) ans * dfs(p.get(i) + 1, p.get(i + 1) - 1) % MOD);
        }
//        if (y.length == 6) System.err.println(l + " - " + r + " : " + ans);
        return ans;
    }

    public void solve(int testNumber, FastReader in, PrintWriter out) {

        int n = 200_004;

        int[] fact = new int[2 * n + 1];
        fact[0] = 1;
        for (int i = 1; i <= 2 * n; i++) {
            fact[i] = (int) ((long) i * fact[i - 1] % MOD);
        }

        int[] rev = new int[n + 3];
        for (int i = 0; i < rev.length; i++) {
            rev[i] = MathUtils.modPow(fact[i], MOD - 2, MOD);
        }

        cat = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int now = fact[2 * i];
            now = (int) ((long) now * rev[i] % MOD);
            now = (int) ((long) now * rev[i + 1] % MOD);
            cat[i] = now;
        }

        int t = in.nextInt();

        for (int it = 0; it < t; ++it) {

            n = in.nextInt();

            y = in.nextArrayInt(n);
            y = ArrayUtils.compress(y);

            poses = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                poses[i] = new ArrayList<>();
            }
            for (int i = 0; i < n; i++) {
                poses[y[i]].add(i);
            }

            table = new SparseTableMax(y);

            out.println(dfs(0, n - 1));

        }
    }
}
