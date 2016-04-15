package code;

import util.FastReader;
import util.FastWriter;

import java.util.Arrays;

public class Painter {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        final int SHIFT = 500_500;
        Tree tree = new Tree(2 * SHIFT);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int color = getColor(in.nextToken());
            int from = in.nextInt() + SHIFT;
            int to = from + in.nextInt();
            tree.paint(from, to, color);
            out.println(tree.getAnswer());
        }
    }

    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int EMPTY = 2;

    public static int getColor(String name) {
        switch (name) {
            case "W":
                return WHITE;
            case "B":
                return BLACK;
            default:
                throw new AssertionError();
        }
    }

    private class Tree {

        private int n;
        private int[] count;
        private int[] length;
        private int[] left;
        private int[] right;
        private int[] upd;

        public Tree(int n) {
            this.n = n;
            count = new int[4 * n];
            length = new int[4 * n];
            left = new int[4 * n];
            Arrays.fill(left, WHITE);
            right = new int[4 * n];
            Arrays.fill(right, WHITE);
            upd = new int[4 * n];
            Arrays.fill(upd, EMPTY);
        }

        private void push(int v, int l, int r) {
            if (upd[v] != EMPTY) {
                int m = (l + r) / 2;
                fillSegment(2 * v + 1, l, m, upd[v]);
                fillSegment(2 * v + 2, m, r, upd[v]);
                upd[v] = EMPTY;
            }
        }

        private void fillSegment(int v, int l, int r, int color) {
            count[v] = color == BLACK ? 1 : 0;
            length[v] = color == BLACK ? (r - l) : 0;
            left[v] = color;
            right[v] = color;
            upd[v] = color;
        }

        private void update(int v) {
            count[v] = count[2 * v + 1] + count[2 * v + 2];
            if (right[2 * v + 1] == BLACK && left[2 * v + 2] == BLACK) {
                count[v]--;
            }
            length[v] = length[2 * v + 1] + length[2 * v + 2];
            left[v] = left[2 * v + 1];
            right[v] = right[2 * v + 2];
        }

        private int from, to, color;

        public void paint(int from, int to, int color) {
            this.from = from;
            this.to = to;
            this.color = color;
            _paint(0, 0, n);
        }

        private void _paint(int v, int l, int r) {
            if (r <= from || to <= l) {
                return;
            }
            if (from <= l && r <= to) {
                fillSegment(v, l, r, color);
                return;
            }
            push(v, l, r);
            int m = (l + r) / 2;
            _paint(2 * v + 1, l, m);
            _paint(2 * v + 2, m, r);
            update(v);
        }

        public String getAnswer() {
            return count[0] + " " + length[0];
        }
    }
}
