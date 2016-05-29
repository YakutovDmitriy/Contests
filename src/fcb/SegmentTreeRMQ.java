package fcb;

public class SegmentTreeRMQ implements RangeMinimumQuery {

    int[] array;
    int[] tree;

    @Override
    public void buildRMQ(int[] array) {
        this.array = array.clone();
        tree = new int[4 * array.length];
        build(0, 0, array.length);
    }

    private void build(int v, int l, int r) {
        if (r - l > 1) {
            int m = (l + r) / 2;
            build(2 * v + 1, l, m);
            build(2 * v + 2, m, r);
            int x = tree[2 * v + 1];
            int y = tree[2 * v + 2];
            tree[v] = array[x] <= array[y] ? x : y;
        } else {
            tree[v] = l;
        }
    }

    @Override
    public int getMinPos(int from, int to) {
        return min(0, 0, array.length, from, to);
    }

    private int min(int v, int l, int r, int from, int to) {
        if (to <= l || r <= from) {
            return -1;
        }
        if (from <= l && r <= to) {
            return tree[v];
        }
        int m = (l + r) / 2;
        int x = min(2 * v + 1, l, m, from, to);
        int y = min(2 * v + 2, m, r, from, to);
        return (y < 0 || (x >= 0 && array[x] <= array[y])) ? x : y;
    }

    @Override
    public String getName() {
        return "sgtree rmq";
    }
}
