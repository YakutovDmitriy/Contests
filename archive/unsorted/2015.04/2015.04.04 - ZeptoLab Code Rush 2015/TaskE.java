package code;

import util.FastReader;
import java.io.PrintWriter;

public class TaskE {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n * 2];
        for (int i = 0; i < a.length; i++) {
            if (i < n) {
                a[i] = in.nextInt();
            } else {
                a[i] = a[i - n];
            }
        }
        long[] pref = new long[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            pref[i + 1] = pref[i] + a[i];
        }
        for (int it = 0; it < q; it++) {
            long b = in.nextLong();
            int[] next = new int[n];
            for (int i = 0, j = 0; i < n; i++) {
                while (j < n + i && pref[j + 1] - pref[i] <= b) {
                    j++;
                }
                next[i] = j;
            }
            int first = -1;
            for (int i = 0; i < n; i++) {
                long sum = pref[next[i]] - pref[i];
                int j = i > 0 ? i - 1 : n - 1;
                sum += a[j];
                if (sum > b && (first == -1 || next[i] - i < next[first] - first)) {
                    first = i;
                }
            }
            if (first == -1) {
                first = 0;
            }
            int ans = Integer.MAX_VALUE;
            for (int i = first; i != next[first]; i++) {
                int now = 0;
                int v = i % n;
                int count = 0;
                while (count < n) {
                    now++;
                    count += next[v] - v;
                    v = next[v] % n;
                }
                ans = Math.min(ans, now);
            }
            out.println(ans);
        }
    }
}
