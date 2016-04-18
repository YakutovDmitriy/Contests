package code;

import util.FastReader;
import util.FastWriter;

public class TaskA {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int a = in.nextInt() - 1;
        int b = in.nextInt();
        out.println((a + n * 1000 + b) % n + 1);
    }
}
