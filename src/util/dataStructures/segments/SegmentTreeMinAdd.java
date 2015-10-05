package util.dataStructures.segments;

public class SegmentTreeMinAdd {

    private final int n;
    private int[] tree;
    private int[] needAdd;

    public SegmentTreeMinAdd(int n) {
        this.n = n;
        tree = new int[4 * n];
        needAdd = new int[4 * n];
    }

    public SegmentTreeMinAdd(int[] array) {
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
        if (needAdd[v] != 0) {
            tree[2 * v + 1] += needAdd[v];
            tree[2 * v + 2] += needAdd[v];
            needAdd[2 * v + 1] += needAdd[v];
            needAdd[2 * v + 2] += needAdd[v];
            needAdd[v] = 0;
        }
    }

    private void update(int v) {
        tree[v] = Math.min(tree[2 * v + 1], tree[2 * v + 2]);
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
            tree[v] += value;
            needAdd[v] += value;
            return;
        }
        push(v);
        int m = (l + r) / 2;
        _add(2 * v + 1, l, m);
        _add(2 * v + 2, m, r);
        update(v);
    }

    public int getMin(int from, int to) {
        this.from = from;
        this.to = to;
        return _getMin(0, 0, n);
    }

    private int _getMin(int v, int l, int r) {
        if (r <= from || to <= l) {
            return Integer.MAX_VALUE;
        }
        if (from <= l && r <= to) {
            return tree[v];
        }
        push(v);
        int m = (l + r) / 2;
        return Math.min(
                _getMin(2 * v + 1, l, m),
                _getMin(2 * v + 2, m, r)
        );
    }

}