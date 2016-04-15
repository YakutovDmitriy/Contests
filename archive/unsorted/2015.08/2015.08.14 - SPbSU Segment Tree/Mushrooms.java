package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.segments.SparseTableMax;

public class Mushrooms {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        SparseTableMax table = new SparseTableMax(in.nextArrayInt(n));
        int m = in.nextInt();
        int from = in.nextInt();
        int to = in.nextInt();
        long ans = 0;
        for (int i = 0; i < m; i++) {
            int now = table.getMax(from, to + 1);
            int newFrom = Math.min(to, (int) (((long) (from + now) * now) % n));
            int newTo = Math.max(to, (int) (((long) (from + now) * now) % n));
            from = newFrom;
            to = newTo;
            ans += now;
        }
        out.println(ans);
    }
}
