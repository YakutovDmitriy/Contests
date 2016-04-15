package code;

import util.FastReader;
import util.FastWriter;

public class Array {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int[] a = in.nextArrayInt(in.nextInt());
        for (int i = 0, j = a.length - 1, it = 0; it < a.length; it++) {
            if (it % 2 == 0) {
                out.println(a[i++]);
            } else {
                out.println(a[j--]);
            }
        }
    }
}
