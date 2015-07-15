package util.dataStructures;

public class BinaryIndexedTreeSum {

    public final int n;
    private long[] a;

    public BinaryIndexedTreeSum(int n) {
        this.n = n;
        a = new long[n + 1];
    }

    public long getPrefixSum(int bound) {
        long res = 0;
        for (int i = bound; i > 0; i -= i & -i) {
            res += a[i];
        }
        return res;
    }

    public long getSum(int l, int r) {
        return getPrefixSum(r) - getPrefixSum(l);
    }

    public void add(int pos, long value) {
        for (int i = pos + 1; i <= n; i += i & -i) {
            a[i] += value;
        }
    }

    public void set(int pos, long value) {
        add(pos, value - getSum(pos, pos + 1));
    }
}