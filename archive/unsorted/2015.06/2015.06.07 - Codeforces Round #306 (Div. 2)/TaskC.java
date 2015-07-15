package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        char[] s = in.nextCharArray();
        int n = s.length;
        for (int i = 0; i < n; i++) {
            s[i] -= '0';
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (s[i] % 8 == 0) ans = s[i];
            for (int j = i + 1; j < n; j++) {
                if ((10 * s[i] + s[j]) % 8 == 0) ans = 10 * s[i] + s[j];
                for (int k = j + 1; k < n; k++) {
                    if ((100 * s[i] + 10 * s[j] + s[k]) % 8 == 0) ans = 100 * s[i] + 10 * s[j] + s[k];
                }
            }
        }
        out.println(ans >= 0 ? ("YES\n" + ans) : "NO");
    }
}
