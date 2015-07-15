package code;

import util.FastReader;
import util.StringUtils;

import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int cnt = in.nextInt();
        char[] s = in.nextToken().toCharArray();
        int[] poses = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            poses[i] = in.nextInt() - 1;
        }
        char[] t = new char[n];
        int last = -1;
        for (int x : poses) {
            int i = Math.max(last + 1, x);
            for (int k = i - x; k < s.length; k++) {
                t[x + k] = s[k];
                last = x + k;
            }
        }
        int[] kmp = StringUtils.KnuthMorrisPratt(s, t, '#');
        int ans = 1;
        for (int x : poses) {
            if (kmp[x] < s.length) {
                ans = 0;
            }
        }
        for (char c : t) {
            if (c == 0) {
                ans = (int) ((long) ans * 26 % 1_000_000_007);
            }
        }
        out.println(ans);
    }
}
