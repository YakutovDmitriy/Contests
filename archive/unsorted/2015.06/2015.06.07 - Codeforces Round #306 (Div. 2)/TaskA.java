package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        char[] s = in.nextCharArray();
        int lab = -1;
        int rab = -1;
        int lba = -1;
        int rba = -1;
        for (int i = 0; i < s.length - 1; i++) {
            if (s[i] == 'A' && s[i + 1] == 'B') {
                if (lab == -1) lab = i;
                rab = i;
            }
            if (s[i] == 'B' && s[i + 1] == 'A') {
                if (lba == -1) lba = i;
                rba = i;
            }
        }
        out.println(lab != -1 && lba != -1 && (rba > lab + 1 || rab > lba + 1) ? "YES" : "NO");
    }
}
