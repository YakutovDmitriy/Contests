package code;

import util.FastReader;
import util.FastWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        char[] s = in.nextCharArray();
        int n = s.length;
        boolean[][] is = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                is[i][j] = s[i] == s[j] && (i + 2 > j - 2 || is[i + 2][j - 2]);
            }
        }
        final int size = n * (n + 1) / 2 + 1;
        int[] there = new int[size];
        int[] count = new int[size];
        int[] a = new int[size];
        int[] b = new int[size];
        Arrays.fill(a, -1);
        Arrays.fill(b, -1);
        int tail = 1;
        for (int i = 0; i < n; i++) {
            int v = 0;
            for (int j = i; j < n; j++) {
                if (s[j] == 'a') {
                    if (a[v] == -1) a[v] = tail++;
                    if (is[i][j]) there[a[v]]++;
                    v = a[v];
                } else {
                    if (b[v] == -1) b[v] = tail++;
                    if (is[i][j]) there[b[v]]++;
                    v = b[v];
                }
            }
        }
        for (int v = tail - 1; v >= 0; v--) {
            count[v] = there[v];
            if (a[v] != -1) count[v] += count[a[v]];
            if (b[v] != -1) count[v] += count[b[v]];
        }
        int k = in.nextInt() - 1;
        StringBuilder sb = new StringBuilder();
        for (int v = 0; there[v] == 0 || k >= there[v]; ) {
            k -= there[v];
            if (a[v] != -1 && count[a[v]] > k) {
                v = a[v];
                sb.append('a');
            } else {
                if (a[v] != -1) {
                    k -= count[a[v]];
                }
                v = b[v];
                sb.append('b');
            }
        }
        out.println(sb);
    }
}
