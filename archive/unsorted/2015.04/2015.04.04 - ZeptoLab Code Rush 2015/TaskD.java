package code;

import util.FastReader;
import util.StringUtils;

import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        char[] s = in.nextToken().toCharArray();
        int[] p = StringUtils.getPrefixFunction(s);
        boolean[] ans = new boolean[n];
        int last = -1;
        for (int i = 0; i < n; i++) {
            if ((i + 1) % k != 0) continue;
            int ab = (i + 1) / k;
            int per = i + 1 - p[i];
            if (ab % per != 0) continue;
            ans[i] = true;
            for (int j = Math.max(last + 1, i + 1); j < n && j - i <= ab && s[j] == s[j - i - 1]; j++) {
                ans[j] = true;
                last = j;
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(ans[i] ? 1 : 0);
        }
        out.println();
    }
}
