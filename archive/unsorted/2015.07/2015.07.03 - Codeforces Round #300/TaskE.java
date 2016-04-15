package code;

import util.FastReader;
import util.FastWriter;

public class TaskE {

    DirectedGraph g;
    int[] max;
    int[] min;
    int[] count;

    void dfs(int v, int player) {
        for (DirectedGraph.Edge e : g.edges[v]) {
            dfs(e.to, player ^ 1);
            count[v] += count[e.to];
        }
        if (count[v] == 0) {
            count[v] = 1;
            return;
        }
        if (player == 0) {
            int ma = Integer.MAX_VALUE;
            int mi = 1;
            for (DirectedGraph.Edge e : g.edges[v]) {
                ma = Math.min(ma, count[e.to] - max[e.to]);
                mi += count[e.to] - min[e.to] - 1;
            }
            max[v] = count[v] - ma;
            min[v] = count[v] - mi;
        } else {
            max[v] = 0;
            min[v] = Integer.MAX_VALUE;
            for (DirectedGraph.Edge e : g.edges[v]) {
                max[v] += max[e.to];
                min[v] = Math.min(min[v], min[e.to]);
            }
        }
    }

    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        g = new DirectedGraph(n);
        for (int i = 0; i < n - 1; i++) {
            g.addEdge(in.nextInt() - 1, in.nextInt() - 1);
        }
        max = new int[n];
        min = new int[n];
        count = new int[n];
        dfs(0, 0);
        out.println(max[0] + 1 + " " + (min[0] + 1));
    }
}
