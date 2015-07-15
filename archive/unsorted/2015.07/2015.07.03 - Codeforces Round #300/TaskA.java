package code;

import util.FastReader;
import util.FastWriter;

public class TaskA {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        String s = in.nextToken();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String t = s.substring(0, i) + s.substring(j);
                if (t.equals("CODEFORCES")) {
                    out.println("YES");
                    return;
                }
            }
        }
        out.println("NO");
    }
}
