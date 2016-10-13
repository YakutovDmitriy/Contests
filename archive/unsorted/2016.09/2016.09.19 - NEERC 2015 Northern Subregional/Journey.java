package code;

import util.FastReader;
import util.FastWriter;

import java.util.Arrays;

public class Journey {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        int[] r = new int[n];
        for (int i = 1; i < n; i++) {
            r[i] = in.nextInt();
        }
        int[] d = new int[n];
        for (int i = 1; i < n - 1; i++) {
            d[i] = in.nextInt();
        }
        int left = 0;
        int right = n;
        while (right - left > 1) {
            int step = (left + right) / 2;
            Tree dp = new Tree(n);
            dp.set(0, 1, 0);
            for (int i = 1; i < n; i++) {
                long now = d[i] + dp.getMin(Math.max(0, i - step), i);
                dp.set(i, i + 1, now);
            }
            if (dp.getMin(n - 1, n) + n - 1 <= t) {
                right = step;
            } else {
                left = step;
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = right; i < r.length; i++) {
            answer = Math.min(answer, r[i]);
        }
        out.println(answer);
    }

    private static class Tree {

        private static final long NONE = Long.MAX_VALUE;

        private final int n;
        private long[] tree;
        private long[] needSet;

        public Tree(int n) {
            this.n = n;
            tree = new long[4 * n];
            needSet = new long[4 * n];
            Arrays.fill(tree, NONE);
            Arrays.fill(needSet, NONE);
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
            tree[v] = Math.min(tree[2 * v + 1], tree[2 * v + 2]);
        }

        private int from;
        private int to;
        private long value;

        public void set(int from, int to, long value) {
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

        public long getMin(int from, int to) {
            this.from = from;
            this.to = to;
            return _getMin(0, 0, n);
        }

        private long _getMin(int v, int l, int r) {
            if (r <= from || to <= l) {
                return NONE;
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

}
