package util.dataStructures.segments.segmentTrees;

public class SegmentTreeSumAdd {

    private final int n;
    private long[] tree;
    private int[] needAdd;

    public SegmentTreeSumAdd(int n) {
        this.n = n;
        tree = new long[4 * n];
        needAdd = new int[4 * n];
    }

    public SegmentTreeSumAdd(int[] array) {
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
        if (needAdd[v] != 0) {
            int m = (l + r) / 2;
            tree[2 * v + 1] += (long) (m - l) * needAdd[v];
            tree[2 * v + 2] += (long) (r - m) * needAdd[v];
            needAdd[2 * v + 1] += needAdd[v];
            needAdd[2 * v + 2] += needAdd[v];
            needAdd[v] = 0;
        }
    }

    private void update(int v) {
        tree[v] = tree[2 * v + 1] + tree[2 * v + 2];
    }

    private int from;
    private int to;
    private int value;

    public void add(int from, int to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
        _add(0, 0, n);
    }

    private void _add(int v, int l, int r) {
        if (r <= from || to <= l) {
            return;
        }
        if (from <= l && r <= to) {
            needAdd[v] += value;
            tree[v] += (long) (r - l) * value;
            return;
        }
        push(v, l, r);
        int m = (l + r) / 2;
        _add(2 * v + 1, l, m);
        _add(2 * v + 2, m, r);
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
        long ret = _getSum(2 * v + 1, l, m) +
                _getSum(2 * v + 2, m, r);
        update(v);
        return ret;
    }
}
