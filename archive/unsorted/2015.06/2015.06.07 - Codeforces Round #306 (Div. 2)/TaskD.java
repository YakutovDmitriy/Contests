package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int k = in.nextInt();
        if (k % 2 == 0) {
            out.println("NO");
            return;
        }
        out.println("YES");
        if (k == 1) {
            out.println("2 1");
            out.println("2 1");
            return;
        }
        int n = 2 * k + 4;
        int m = n * k / 2;
        out.println(n + " " + m);
        int size = 0;
        for (int add = 0; add <= k + 2; add += k + 2) {
            for (int i = 2; i <= k; i++) {
                size++;
                out.println((add + 1) + " " + (add + i));
            }
            for (int i = 2; i <= k; i++) {
                for (int j = i + 2 - i % 2; j <= k; j++) {
                    size++;
                    out.println((add + i) + " " + (add + j));
                }
            }
            for (int i = 2; i <= k; i++) {
                out.println((i + add) + " " + (k + 1 + add));
                out.println((i + add) + " " + (k + 2 + add));
                size += 2;
            }
            size++;
            out.println((add + k + 1) + " " + (add + k + 2));
        }
        size++;
        out.println(1 + " " + (k + 3));
    }
}
