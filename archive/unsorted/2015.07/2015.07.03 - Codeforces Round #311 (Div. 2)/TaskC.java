package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.PairIntInt;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] l = in.nextArrayInt(n);
        int[] d = in.nextArrayInt(n);
        PairIntInt[] a = new PairIntInt[n];
        for (int i = 0; i < n; i++) {
            a[i] = new PairIntInt(l[i], d[i]);
        }
        Arrays.sort(a);
        int[] sums = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            sums[i] = sums[i + 1] + a[i].second;
        }
        int[] cnt = new int[300];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && a[i].first == a[j].first) {
                j++;
            }
            int need = Math.max(0, i + 1 - (j - i));
            int now = sums[j];
            for (int t = 0; need > 0; t++) {
                int c = Math.min(need, cnt[t]);
                need -= c;
                now += c * t;
            }
            ans = Math.min(ans, now);
            for (; i < j; i++) {
                cnt[a[i].second]++;
            }
        }
        out.println(ans);
    }
}
