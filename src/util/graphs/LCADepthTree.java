package util.graphs;

import java.util.ArrayList;

public class LCADepthTree {

    public final int n;
    public final int root;
    public final int log;
    public ArrayList<Integer>[] edges;
    public int[] depth;
    public int[][] pr;

    public LCADepthTree(int n, int root, int log) {
        this.n = n;
        this.root = root;
        this.log = log;
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
        edges[to].add(from);
    }

    private void buildDFS(int v, int prev, int d) {
        depth[v] = d++;
        pr[v][0] = prev;
        for (int i = 1; i < pr[v].length; i++) {
            pr[v][i] = pr[pr[v][i - 1]][i - 1];
        }
        for (Integer to : edges[v]) {
            if (to != prev) {
                buildDFS(to, v, d);
            }
        }
    }

    public void build() {
        depth = new int[n];
        pr = new int[n][log];
        buildDFS(root, root, 0);
    }

    public void addVertex(int vertex, int ancestor) {
        depth[vertex] = depth[ancestor] + 1;
        pr[vertex][0] = ancestor;
        for (int i = 1; i < pr[vertex].length; i++) {
            pr[vertex][i] = pr[pr[vertex][i - 1]][i - 1];
        }
    }

    public int goUp(int v, int len) {
        if (len <= 0) {
            return v;
        }
        for (int i = 0; len > 0; i++) {
            if ((len & 1) == 1) {
                v = pr[v][i];
            }
            len >>= 1;
        }
        return v;
    }

    public int getLCA(int a, int b) {
        a = goUp(a, depth[a] - depth[b]);
        b = goUp(b, depth[b] - depth[a]);
        if (a == b) {
            return a;
        }
        for (int i = pr[a].length; i >= 0; i--) {
            int ta = pr[a][i];
            int tb = pr[b][i];
            if (ta != tb) {
                a = ta;
                b = tb;
            }
        }
        return pr[a][0];
    }
}