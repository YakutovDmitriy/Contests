package fcb;

import util.FastReader;
import util.FastWriter;
import util.RandomGenerator;
import util.dataStructures.pairs.Pair;
import util.dataStructures.pairs.PairIntInt;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Check {

    Random random = RandomGenerator.RANDOM;

    long time() {
        return System.currentTimeMillis();
    }

    boolean checkRMQ(int[] array, int iters, RangeMinimumQuery... rmqs) {
        int n = rmqs.length;

        long[] buildTime = new long[n];
        for (int i = 0; i < n; i++) {
            long start = time();
            rmqs[i].buildRMQ(array);
            buildTime[i] = time() - start;
        }

        long[] sumQueries = new long[n];

        for (int it = 0; it < iters; it++) {

            int from = array.length == 1 ? 0 : random.nextInt(array.length - 1);
            int to = from == array.length - 1 ? array.length : random.nextInt(array.length - 1 - from) + from + 1;

            int[] answers = new int[n];

            for (int i = 0; i < n; i++) {
                long start = time();
                answers[i] = rmqs[i].getMinPos(from, to);
                sumQueries[i] += time() - start;
            }

            boolean correct = true;

            for (int i = 1; i < n; i++) {
                correct &= answers[i] == answers[0];
            }

            if (!correct) {
                out.println("Check failed on array of length " + array.length + ", itetation #" + it + " in query [" + from + "; " + to + ")");
                if (array.length <= 25) {
                    out.println(Arrays.toString(array));
                }
                for (int i = 0; i < n; i++) {
                    out.println(rmqs[i].getName() + ":" + "\t\t" + "pos: " + answers[i] + "\t value: " + array[answers[i]]);
                }
                System.out.println();
                return false;
            }

        }

        double[] avg = new double[n];

        for (int i = 0; i < n; i++) {
            avg[i] = (double) sumQueries[i] / iters;
        }

        out.println("Success on array of length " + array.length);
        if (array.length <= 25) {
            out.println(Arrays.toString(array));
        }

        for (int i = 0; i < n; i++) {
            out.println(rmqs[i].getName() + " built in " + buildTime[i] + " ms" +
                    " with average time on query " + String.format("%.4f ms", avg[i]) +
                    " and summary time " + String.format("%.3f", 0.001 * sumQueries[i]) + " sec for " + iters + " queries");
        }
        System.out.println();

        return true;
    }

    boolean checkLCA(List<PairIntInt> edges, int root, int iters, LCATree... trees) {
        int n = trees.length;

        int vertices = edges.size() + 1;

        long[] buildTime = new long[n];
        for (int i = 0; i < n; i++) {
            long start = time();
            trees[i].buildTree(edges, root);
            buildTime[i] = time() - start;
        }

        long[] sumQueries = new long[n];

        for (int it = 0; it < iters; it++) {

            int v = random.nextInt(vertices);
            int u = random.nextInt(vertices);

            int[] answers = new int[n];

            for (int i = 0; i < n; i++) {
                long start = time();
                answers[i] = trees[i].getLCA(v, u);
                sumQueries[i] += time() - start;
            }

            boolean correct = true;

            for (int i = 1; i < n; i++) {
                correct &= answers[i] == answers[0];
            }

            if (!correct) {
                out.println("Check failed on tree of size " + vertices + ", itetation #" + it + " in query (" + v + ", " + u + ")");
                if (vertices <= 10) {
                    out.println("Tree:   root: " + root);
                    for (PairIntInt edge : edges) {
                        out.println("  " + edge.first + " " + edge.second);
                    }
                }
                for (int i = 0; i < n; i++) {
                    out.println(trees[i].getName() + ":" + "\t\t" + "lca: " + answers[i]);
                }
                System.out.println();
                return false;
            }
        }

        double[] avg = new double[n];

        for (int i = 0; i < n; i++) {
            avg[i] = (double) sumQueries[i] / iters;
        }

        out.println("Success on tree of size " + vertices);

        for (int i = 0; i < n; i++) {
            out.println(trees[i].getName() + " built in " + buildTime[i] + " ms " +
                    "with average time on query " + String.format("%.4f ms", avg[i]) +
                    " and summary time " + 0.001 * sumQueries[i] + " sec for " + iters + " queries");
        }
        System.out.println();

        return true;

    }

    void solve() {

        final int LEN = 9300300;
        final int ITERS = LEN;

        for (int len = LEN; len <= LEN; ++len) {
            checkRMQ(
                    RandomGenerator.randomArray(len),
                    ITERS,
                    new SparseTableRMQ(),
//                    new SegmentTreeRMQ(),
                    new FarachColtonBenderRMQ()
            );
        }
    }

    private int[] randomArrayPM1(int length) {
        int[] ret = new int[length];
        ret[0] = random.nextInt(10);
        for (int i = 1; i < ret.length; i++) {
            ret[i] = ret[i - 1] + (random.nextBoolean() ? 1 : -1);
        }
        return ret;
    }

    static FastReader in;
    static FastWriter out;

    static PrintStream err;

    public static void main(String[] args) {
        try {
            in = new FastReader(System.in);
            out = new FastWriter(System.out, true);
            err = System.err;
            new Check().solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
