package code;

import util.FastReader;
import util.FastWriter;

public class Fenwick {

    class Tree {

        int n, m;
        int[][] a;

        public Tree(int n, int m) {
            this.n = n;
            this.m = m;
            a = new int[n + 1][m + 1];
        }

        void add(int x, int y, int value) {
            for (int i = x; i < n; i |= (i + 1)) {
                for (int j = y; j < m; j |= (j + 1)) {
                    a[i][j] += value;
               }
            }
        }

        int get(int x, int y) {

        }
    }

    public void solve(int testNumber, FastReader in, FastWriter out) {
        final int SHIFT = 1007;
        in.nextInt();
        int m = in.nextInt();
        Tree tree = new Tree(2 * SHIFT, 2 * SHIFT);
        for (int i = 0; i < m; i++) {
            switch (in.nextToken()) {
                case "ADD":
                    tree.add(in.nextInt() + SHIFT, in.nextInt() + SHIFT, 1);
                    break;
                case "GET":
                    int fromX = in.nextInt() + SHIFT;
                    int fromY = in.nextInt() + SHIFT;
                    int toX = in.nextInt() + 1 + SHIFT;
                    int toY = in.nextInt() + 1 + SHIFT;
                    out.println(tree.getSum(fromX, toX, fromY, toY));
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
