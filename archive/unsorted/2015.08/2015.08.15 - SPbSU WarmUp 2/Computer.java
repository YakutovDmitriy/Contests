package code;

import util.FastReader;
import util.FastWriter;

public class Computer {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        char[] s = in.nextCharArray();
        int cnt = 0;
        for (int i = 1; i < s.length; i++) {
            if (s[i] != s[i - 1]) {
                cnt++;
            }
        }
        if (s[0] == '1') {
            cnt++;
        }
        out.println(cnt);
    }
}
