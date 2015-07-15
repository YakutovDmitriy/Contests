package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] a = new int[k][k];
        for (int it = 0; it < n; it++) {
            int len = in.nextInt();
            int x = -1;
            int y = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                int cnt = 0;
                for (int j = 0; j < len - 1; j++) {
                    cnt += a[i][j];
                }
                for (int j = len - 1; j < k; j++) {
                    cnt += a[i][j];
                    if (cnt == 0) {
                        int now = 0;
                        for (int t = j - len + 1; t <= j; t++) {
                            now += Math.abs(i - k / 2) + Math.abs(t - k / 2);
                        }
                        if (now < min) {
                            min = now;
                            x = i;
                            y = j - len + 1;
                        }
                    }
                    cnt -= a[i][j - len + 1];
                }
            }
            if (x > -1) {
                for (int i = y; i < y + len; i++) {
                    a[x][i] = 1;
                }
                x++;
                y++;
                out.println(x + " " + y + " " + (y + len - 1));
            } else {
                out.println(-1);
            }
        }
    }
}
