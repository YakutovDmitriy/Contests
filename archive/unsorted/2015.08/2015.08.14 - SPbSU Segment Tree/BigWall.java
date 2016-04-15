package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.segments.SegmentTreeMaxAdd;

public class BigWall {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        SegmentTreeMaxAdd tree = new SegmentTreeMaxAdd(n);
        for (int it = 0; it < m; it++) {
            switch (in.nextToken()) {
                case "build":
                    tree.add(in.nextInt() - 1, in.nextInt(), in.nextInt());
                    break;
                case "test":
                    out.println(tree.getMax(in.nextInt() - 1, in.nextInt()));
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
