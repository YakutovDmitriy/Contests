package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskE {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextArrayInt(n);
        boolean bad  = a[n - 1] == 1;
        int ones = 0;
        for (int x : a) {
            ones += x;
        }
        bad |= ones == n - 2 && a[n - 1] == 0 && a[n - 2] == 0;
        if (bad) {
            out.println("NO");
            return;
        }
        out.println("YES");
        if (n == 1) {
            out.println("0");
            return;
        }
        if (n == 2) {
            out.println("1->0");
            return;
        }
        if (a[n - 2] == 1) {
            for (int i = 0; i < n; i++) {
                if (i > 0) out.print("->");
                out.print(a[i]);
            }
        } else if (a[n - 3] == 0) {
            /// 1 1 1 1 (0->0) 0
            for (int i = 0; i < n; i++) {
                if (i > 0) out.print("->");
                if (i == n - 3) out.print('(');
                out.print(a[i]);
                if (i == n - 2) out.print(')');
            }
        } else {
            /// ..... 1 0 0
            int zero = n - 3;
            while (a[zero] != 0) {
                zero--;
            }
            /// 0 .... 1 0 0
            /// (0 (... 1 0)) 0
            for (int i = 0; i < n; i++) {
                if (i > 0) out.print("->");
                if (i == zero || i == zero + 1) out.print('(');
                out.print(a[i]);
                if (i == n - 2) out.print("))");
            }
        }
        out.println();
    }
}
