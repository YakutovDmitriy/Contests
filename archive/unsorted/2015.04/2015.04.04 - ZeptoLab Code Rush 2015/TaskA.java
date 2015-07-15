package code;

import util.FastReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] s = in.nextToken().toCharArray();
        for (int i = 0; i < n; i++) {
            for (int d = 1; i + 4 * d < n; d++) {
                boolean ok = true;
                for (int j = 0; j < 5; ++j) {
                    ok &= s[i + j * d] != '.';
                }
                if (ok) {
                    out.println("yes");
                    return;
                }
            }
        }
        out.println("no");
    }
}
