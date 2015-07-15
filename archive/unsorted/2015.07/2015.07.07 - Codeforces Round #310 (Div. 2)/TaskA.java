package code;

import util.FastReader;
import util.FastWriter;

public class TaskA {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int zeros = 0;
        int ones = 0;
        int n = in.nextInt();
        for (char s : in.nextCharArray()) {
            if (s == '0') {
                zeros++;
            } else {
                ones++;
            }
        }
        int delete = Math.min(zeros, ones) * 2;
        out.println(n - delete);
    }
}
