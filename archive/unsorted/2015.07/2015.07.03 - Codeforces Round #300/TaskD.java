package code;

import util.FastReader;
import util.FastWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        char[][] s = in.nextTable(n, n);
        char[][] res = new char[2 * n - 1][2 * n - 1];
        for (int i = 0; i < res.length; i++) {
            Arrays.fill(res[i], 'x');
        }
        res[n - 1][n - 1] = 'o';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s[i][j] == 'o') {
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if (s[x][y] == '.') {
                                res[n - 1 + x - i][n - 1 + y - j] = '.';
                            }
                        }
                    }
                }
            }
        }
        char[][] now = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(now[i], '.');
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s[i][j] == 'o') {
                    now[i][j] = 'o';
                    for (int dx = -n + 1; dx < n; ++dx) {
                        for (int dy = -n + 1; dy < n; ++dy) {
                            if (i + dx >= 0 & i + dx < n && j + dy >= 0 && j + dy < n) {
                                if (now[i + dx][j + dy] == '.' && res[n - 1 + dx][n - 1 + dy] == 'x') {
                                    now[i + dx][j + dy] = 'x';
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (now[i][j] != s[i][j]) {
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
        for (int i = 0; i < res.length; i++) {
            out.println(res[i]);
        }
    }
}
