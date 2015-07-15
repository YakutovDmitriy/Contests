package code;

import util.FastReader;
import util.graphs.KuhnGraph;

import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }
        KuhnGraph g = new KuhnGraph(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (Math.abs(a[i] - b[j]) <= 1) {
                    g.addEdge(i, j);
                }
            }
        }
        out.println(g.findMatchingSize());
    }
}
