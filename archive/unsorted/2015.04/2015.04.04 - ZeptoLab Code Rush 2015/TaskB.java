package code;

import util.FastReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        n = in.nextInt();
        b = 1 << (n + 1);
        a = new int[b];
        mo = new int[b];
        sum = new int[10 * b + 1000];
        for (int i = 2; i < b; i++) {
            a[i] = in.nextInt();
        }
        dfs(1);
        int ans = 0;
        for (int i = 2; i < b; i++) {
            ans += mo[i];
        }
        out.println(ans);
    }

    int n;
    int b;
    int[] a;
    int[] mo;
    int[] sum;

    void dfs(int v) {
        if (v >= b / 2) return;
        dfs(2 * v);
        dfs(2 * v + 1);
        int s1 = sum[2 * v] + a[2 * v];
        int s2 = sum[2 * v + 1] + a[2 * v + 1];
        if (s1 > s2) {
            mo[2 * v + 1] += s1 - s2;
        } else {
            mo[2 * v] += s2 - s1;
        }
        sum[v] = s1 + mo[2 * v];
    }
}
