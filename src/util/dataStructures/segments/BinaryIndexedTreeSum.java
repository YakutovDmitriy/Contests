package util.dataStructures.segments;

public class BinaryIndexedTreeSum {

    public final int n;
    private int[] a;

    public BinaryIndexedTreeSum(int n) {
        this.n = n;
        a = new int[n + 1];
    }

    public int getPrefixSum(int bound) {
        int res = 0;
        for (int i = bound; i > 0; i -= i & -i) {
            res += a[i];
        }
        return res;
    }

    public int getSum(int l, int r) {
        return getPrefixSum(r) - getPrefixSum(l);
    }

    public void add(int pos, int value) {
        for (int i = pos + 1; i <= n; i += i & -i) {
            a[i] += value;
        }
    }

    public void set(int pos, int value) {
        add(pos, value - getSum(pos, pos + 1));
    }
}