package util.graphs;

import java.util.ArrayList;

public class LCATimerTree {

    public final int n;
    public final int root;
    public ArrayList<Integer>[] edges;
    public int[][] pr;
    public int[] tin;
    public int[] tout;

    private int timer;

    public LCATimerTree(int n, int root) {
        this.n = n;
        this.root = root;
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
        edges[to].add(from);
    }

    private void buildDFS(int v, int prev) {
        pr[v][0] = prev;
        for (int i = 1; i < pr[v].length; i++) {
            pr[v][i] = pr[pr[v][i - 1]][i - 1];
        }
        tin[v] = timer++;
        for (Integer to : edges[v]) {
            if (to != prev) {
                buildDFS(to, v);
            }
        }
        tout[v] = timer++;
    }

    public void build() {
        int log = 1;
        while ((1 << log) < n) {
            log++;
        }
        pr = new int[n][log];
        tin = new int[n];
        tout = new int[n];
        buildDFS(root, root);
    }

    public boolean isAncestor(int anc, int ver) {
        return tin[anc] <= tin[ver] && tout[ver] <= tout[anc];
    }

    public int getLCA(int a, int b) {
        if (isAncestor(a, b)) {
            return a;
        }
        for (int i = pr[a].length - 1; i >= 0; i--) {
            if (!isAncestor(pr[a][i], b)) {
                a = pr[a][i];
            }
        }
        return pr[a][0];
    }
}
