package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.segments.SegmentTreeSumAdd;

public class Sum2 {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        SegmentTreeSumAdd tree = new SegmentTreeSumAdd(in.nextArrayInt(in.nextInt()));
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            out.println(tree.getSum(in.nextInt() - 1, in.nextInt()));
        }
    }
}
