package util.dataStructures.segments;

import java.util.Arrays;

public class SegmentTreeMaxSet {

    private static final int NONE = Integer.MIN_VALUE;

    private final int n;
    private int[] tree;
    private int[] needSet;

    public SegmentTreeMaxSet(int n) {
        this.n = n;
        tree = new int[4 * n];
        needSet = new int[4 * n];
        Arrays.fill(tree, NONE);
        Arrays.fill(needSet, NONE);
    }

    public SegmentTreeMaxSet(int[] array) {
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

    private void push(int v) {
        if (needSet[v] != NONE) {
            tree[2 * v + 1] = needSet[v];
            tree[2 * v + 2] = needSet[v];
            needSet[2 * v + 1] = needSet[v];
            needSet[2 * v + 2] = needSet[v];
            needSet[v] = NONE;
        }
    }

    private void update(int v) {
        tree[v] = Math.max(tree[2 * v + 1], tree[2 * v + 2]);
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
            tree[v] = value;
            needSet[v] = value;
            return;
        }
        push(v);
        int m = (l + r) / 2;
        _set(2 * v + 1, l, m);
        _set(2 * v + 2, m, r);
        update(v);
    }

    public int getMax(int from, int to) {
        this.from = from;
        this.to = to;
        return _getMax(0, 0, n);
    }

    private int _getMax(int v, int l, int r) {
        if (r <= from || to <= l) {
            return NONE;
        }
        if (from <= l && r <= to) {
            return tree[v];
        }
        push(v);
        int m = (l + r) / 2;
        return Math.max(
                _getMax(2 * v + 1, l, m),
                _getMax(2 * v + 2, m, r)
        );
    }
}
