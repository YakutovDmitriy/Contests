package util.dataStructures.segments;

public class BinaryIndexedTreeSum2D {

    public final int n;
    public final int m;
    private long[][] a;

    public BinaryIndexedTreeSum2D(int n, int m) {
        this.n = n;
        this.m = m;
        a = new long[n + 1][m + 1];
    }

    public long getPrefixSum(int boundX, int boundY) {
        long res = 0;
        for (int i = boundX; i > 0; i -= i & -i) {
            for (int j = boundY; j > 0; j -= j & -j) {
                res += a[i][j];
            }
        }
        return res;
    }

    public long getSum(int fromX, int toX, int fromY, int toY) {
        return getPrefixSum(toX, toY) + getPrefixSum(fromX, fromY) -
                getPrefixSum(toX, fromY) - getPrefixSum(fromX, toY);
    }

    public void add(int x, int y, long value) {
        for (int i = x + 1; i <= n; i += i & -i) {
            for (int j = y + 1; j <= m; j += j & -j) {
                a[i][j] += value;
            }
        }
    }

    public void set(int x, int y, long value) {
        add(x, y, value - getSum(x, x + 1, y, y + 1));
    }
}