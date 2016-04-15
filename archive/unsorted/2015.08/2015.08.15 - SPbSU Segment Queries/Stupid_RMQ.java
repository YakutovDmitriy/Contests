package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.segments.SparseTableMin;

public class Stupid_RMQ {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        SparseTableMin table = new SparseTableMin(in.nextArrayInt(in.nextInt()));
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            out.println(table.getMin(in.nextInt() - 1, in.nextInt()));
        }
    }
}
