package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.PairIntInt;

import java.util.Arrays;

public class Escape {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt() + 2;
        int w = in.nextInt();
        PairIntInt[] a = new PairIntInt[n];
        a[0] = new PairIntInt(in.nextInt(), -1);
        a[n - 1] = new PairIntInt(in.nextInt(), -1);
        for (int i = 1; i < n - 1; i++) {
            a[i] = new PairIntInt(in.nextInt(), i);
        }
        Arrays.sort(a);
        int l = -1;
        int r = n - 1;
        int ans = -1;
        while (r - l > 1) {
            int cnt = (l + r) / 2;
            boolean ok = false;
            for (int i = 1; !ok && i + cnt < n; i++) {
                if (a[i + cnt].first - a[i - 1].first >= w) {
                    ok = true;
                    ans = i;
                }
            }
            if (ok) {
                r = cnt;
            } else {
                l = cnt;
            }
        }
        if (r == n - 1) {
            out.println(-1);
        } else {
            out.println(r);
            for (int i = ans; i < ans + r; i++) {
                out.println(a[i].second);
            }
        }
    }
}
