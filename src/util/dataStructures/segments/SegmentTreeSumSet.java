package util.dataStructures.segments;

import java.util.Arrays;

public class SegmentTreeSumSet {

    private static final int NONE = Integer.MIN_VALUE;

    private final int n;
    private long[] tree;
    private int[] needSet;

    public SegmentTreeSumSet(int n) {
        this.n = n;
        tree = new long[4 * n];
        needSet = new int[4 * n];
        Arrays.fill(needSet, NONE);
    }

    public SegmentTreeSumSet(int[] array) {
        this(array.length);
        build(0, 0, n, array);
    }

    public int size() {
        return n;
    }

    private void build(int v, int l, int r, int[] a) {
        if (r - l == 1) {
            tree[v] = a[l];
        } else {
            int m = (l + r) / 2;
            build(2 * v + 1, l, m, a);
            build(2 * v + 2, m, r, a);
            update(v);
        }
    }

    private void push(int v, int l, int r) {
        if (needSet[v] != NONE) {
            int m = (l + r) / 2;
            tree[2 * v + 1] = (long) (m - l) * needSet[v];
            tree[2 * v + 2] = (long) (r - m) * needSet[v];
            needSet[2 * v + 1] = needSet[v];
            needSet[2 * v + 2] = needSet[v];
            needSet[v] = NONE;
        }
    }

    private void update(int v) {
        tree[v] = tree[2 * v + 1] + tree[2 * v + 2];
    }

    private int from;
    private int to;
    private int value;

    public void set(int from, int to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
        _set(0, 0, n);
    }

    private void _set(int v, int l, int r) {
        if (r <= from || to <= l) {
            return;
        }
        if (from <= l && r <= to) {
            needSet[v] = value;
            tree[v] = (long) (r - l) * value;
            return;
        }
        push(v, l, r);
        int m = (l + r) / 2;
        _set(2 * v + 1, l, m);
        _set(2 * v + 2, m, r);
        update(v);
    }

    public long getSum(int from, int to) {
        this.from = from;
        this.to = to;
        return _getSum(0, 0, n);
    }

    private long _getSum(int v, int l, int r) {
        if (r <= from || to <= l) {
            return 0;
        }
        if (from <= l && r <= to) {
            return tree[v];
        }
        push(v, l, r);
        int m = (l + r) / 2;
        return _getSum(2 * v + 1, l, m) +
                _getSum(2 * v + 2, m, r);
    }
}
