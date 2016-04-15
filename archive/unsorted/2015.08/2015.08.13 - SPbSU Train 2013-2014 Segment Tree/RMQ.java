package code;

import util.FastReader;
import util.FastWriter;

public class RMQ {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] l = new int[m];
        int[] r = new int[m];
        int[] q = new int[m];
        for (int i = 0; i < m; i++) {
            l[i] = in.nextInt() - 1;
            r[i] = in.nextInt();
            q[i] = in.nextInt();
        }
        Tree tree = new Tree(n);
        for (int i = 0; i < m; i++) {
            tree.modify(l[i], r[i], q[i]);
        }
        for (int i = 0; i < m; i++) {
            if (tree.getMin(l[i], r[i]) != q[i]) {
                out.println("inconsistent");
                return;
            }
        }
        out.println("consistent");
        out.printArray(tree.toArray());
    }

    class Tree {
        private int[] mi;
        private int[] upd;
        private int n;

        public Tree(int n) {
            this.n = n;
            mi = new int[4 * n];
            upd = new int[4 * n];
            build(0, 0, n);
        }

        private void build(int v, int l, int r) {
            if (r - l > 1) {
                int m = (l + r) / 2;
                build(2 * v + 1, l, m);
                build(2 * v + 2, m, r);
            }
            mi[v] = Integer.MIN_VALUE;
            upd[v] = Integer.MIN_VALUE;
        }

        public int[] toArray() {
            int[] ret = new int[n];
            toArray(0, 0, n, ret);
            return ret;
        }

        private void toArray(int v, int l, int r, int[] ret) {
            if (r - l == 1) {
                ret[l] = mi[v];
            } else {
                push(v);
                int m = (l + r) / 2;
                toArray(2 * v + 1, l, m, ret);
                toArray(2 * v + 2, m, r, ret);
            }
        }

        private void push(int v) {
            int x = 2 * v + 1;
            mi[x] = Math.max(mi[x], upd[v]);
            upd[x] = Math.max(upd[x], upd[v]);
            x = 2 * v + 2;
            mi[x] = Math.max(mi[x], upd[v]);
            upd[x] = Math.max(upd[x], upd[v]);
        }

        public void modify(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
            _modify(0, 0, n);
        }

        private int from, to, value;

        private void _modify(int v, int l, int r) {
            if (r <= from || to <= l) {
                return;
            }
            if (from <= l && r <= to) {
                mi[v] = Math.max(mi[v], value);
                upd[v] = Math.max(upd[v], value);
                return;
            }
            push(v);
            int m = (l + r) / 2;
            _modify(2 * v + 1, l, m);
            _modify(2 * v + 2, m, r);
            mi[v] = Math.min(mi[2 * v + 1], mi[2 * v + 2]);
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
                return mi[v];
            }
            push(v);
            int m = (l + r) / 2;
            return Math.min(_getMin(2 * v + 1, l, m),
                    _getMin(2 * v + 2, m, r));
        }
    }
}
