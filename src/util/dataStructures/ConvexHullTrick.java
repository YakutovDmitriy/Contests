package util.dataStructures;

import util.AlgoUtils;

/*
 * Add lines Y = K X + B with increasing K
 * By given X find line (K, B) such that the value of Y is maximal
 */
public class ConvexHullTrick {

    private final long INFINITY;

    private long[] ks;
    private long[] bs;
    private long[] from;
    private int size;

    public ConvexHullTrick(int capacity, long infinity) {
        INFINITY = infinity;
        ks = new long[capacity];
        bs = new long[capacity];
        from = new long[capacity];
        size = 0;
    }

    public void addLine(long k, long b) {
        while (size > 0 && intersect(k, b) <= from[size - 1]) {
            size--;
        }
        ks[size] = k;
        bs[size] = b;
        from[size] = intersect(k, b);
        size++;
    }

    public long evaluate(long x) {
        int pos = AlgoUtils.upperBound(from, 0, size, x) - 1;
        return ks[pos] * x + bs[pos];
    }

    private long intersect(long k, long b) {
        if (size == 0) {
            return -INFINITY;
        }
        return (bs[size - 1] - b + k - ks[size - 1] - 1) / (k - ks[size - 1]);
    }
}
