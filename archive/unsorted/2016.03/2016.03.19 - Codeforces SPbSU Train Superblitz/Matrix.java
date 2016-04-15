package code;

import util.FastReader;
import util.FastWriter;
import util.Hungarian;

public class Matrix {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[][] a = in.nextArray2Int(n, n);
        Hungarian h = new Hungarian(a, Integer.MAX_VALUE / 2);
        out.println(h.getSummaryCost());
        for (int i = 0; i < h.size(); i++) {
            out.println(i + 1 + " " + (h.px[i] + 1));
        }
    }
}
