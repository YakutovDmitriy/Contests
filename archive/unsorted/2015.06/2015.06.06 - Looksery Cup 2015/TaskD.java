package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] s = new char[n][];
        for (int i = 0; i < n; i++) {
            s[i] = in.nextToken().toCharArray();
        }
        int[][] cnt = new int[n][m];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int need = s[i][j] == 'B' ? -1 : 1;
                if (need != cnt[i][j]) {
                    ans++;
                    int diff = need - cnt[i][j];
                    for (int i1 = 0; i1 <= i; i1++) {
                        for (int j1 = 0; j1 <= j; j1++) {
                            cnt[i1][j1] += diff;
                        }
                    }
                }
            }
        }
        out.println(ans);
    }
}
