package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] s = new char[n][];
        for (int i = 0; i < n; i++) {
            s[i] = in.nextToken().toCharArray();
        }
        int ans = 0;
        for (int i = 0; i + 1 < n; i++) {
            for (int j = 0; j + 1 < m; j++) {
                int x = 0;
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        char c = s[i + k][j + l];
                        if (c == 'f') x |= 1;
                        if (c == 'a') x |= 2;
                        if (c == 'c') x |= 4;
                        if (c == 'e') x |= 8;
                    }
                }
                if (x == 15) {
                    ans++;
                }
            }
        }
        out.println(ans);
    }
}
