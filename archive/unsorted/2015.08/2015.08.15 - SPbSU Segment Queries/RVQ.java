package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.segments.SegmentTreeMaxSet;
import util.dataStructures.segments.SegmentTreeMinSet;

public class RVQ {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        final int n = 100_100;
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = (int) ((long) i * i % 12345 + (long) i * i * i % 23456);
        }
        SegmentTreeMinSet min = new SegmentTreeMinSet(a);
        SegmentTreeMaxSet max = new SegmentTreeMaxSet(a);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            if (x > 0) {
                int y = in.nextInt();
                x--;
                out.println(max.getMax(x, y) - min.getMin(x, y));
            } else {
                int value = in.nextInt();
                x = -x - 1;
                min.set(x, x + 1, value);
                max.set(x, x + 1, value);
            }
        }
    }
}
