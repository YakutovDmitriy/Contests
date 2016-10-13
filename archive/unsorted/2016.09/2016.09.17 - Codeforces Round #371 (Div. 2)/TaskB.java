package code;

import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;

public class TaskB {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int[] a = ArrayUtils.sortAndUnique(in.nextArrayInt(in.nextInt()));
        switch (a.length) {
            case 1:
            case 2:
                out.println("YES");
                break;
            case 3:
                out.println(a[0] - a[1] == a[1] - a[2] ? "YES" : "NO");
                break;
            default:
                out.println("NO");
        }
    }
}
