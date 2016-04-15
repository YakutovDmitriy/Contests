package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.segments.SegmentTreeSumSet;

public class Sum {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        SegmentTreeSumSet tree = new SegmentTreeSumSet(n);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            switch (in.nextToken()) {
                case "A":
                    tree.set(in.nextInt() - 1, in.nextInt(), in.nextInt());
                    break;
                case "Q":
                    out.println(tree.getSum(in.nextInt() - 1, in.nextInt()));
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
