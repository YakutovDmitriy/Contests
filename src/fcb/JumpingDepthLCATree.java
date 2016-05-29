package fcb;

import util.dataStructures.DSU;
import util.dataStructures.pairs.PairIntInt;

import java.util.ArrayList;
import java.util.List;

public class JumpingDepthLCATree implements LCATree {

    private int[] depth;
    private int[][] pr;

    @Override
    public void buildTree(List<PairIntInt> edges, int root) {
        int n = edges.size() + 1;
        {
            for (PairIntInt edge : edges) {
                for (int v : edge.getFirstAndSecond()) {
                    if (v < 0 || v >= n) {
                        throw new UnsupportedOperationException("vertices in tree must have numbers from 0 to edges.size()");
                    }
                }
            }
            DSU dsu = new DSU(n);
            for (PairIntInt edge : edges) {
                dsu.unite(edge.first, edge.second);
            }
            for (int i = 0; i < n; i++) {
                if (dsu.getRootOf(i) != dsu.getRootOf(0)) {
                    throw new UnsupportedOperationException("graph must be connected");
                }
            }
        }
        int log = 0;
        while ((1 << log) <= n) {
            log++;
        }
        pr = new int[log][n];
        List<Integer>[] adjacents = new List[n];
        for (int i = 0; i < adjacents.length; i++) {
            adjacents[i] = new ArrayList<>();
        }
        for (PairIntInt edge : edges) {
            int a = edge.first;
            int b = edge.second;
            adjacents[a].add(b);
            adjacents[b].add(a);
        }
        int[] queue = new int[n];
        depth = new int[n];
        int tail = 0;
        queue[tail++] = root;
        for (int i = 0; i < log; i++) {
            pr[i][root] = root;
        }
        for (int head = 0; head < tail; head++) {
            int v = queue[head];
            for (int to : adjacents[v]) {
                if (to != pr[0][v]) {
                    queue[tail++] = to;
                    depth[to] = depth[v] + 1;
                    pr[0][to] = v;
                    for (int i = 1; i < log; i++) {
                        pr[i][to] = pr[i - 1][pr[i - 1][to]];
                    }
                }
            }
        }
    }

    @Override
    public int getLCA(int a, int b) {
        for (int i = pr.length - 1; i >= 0; --i) {
            if ((1 << i) <= depth[a] - depth[b]) {
                a = pr[i][a];
            }
        }
        for (int i = pr.length - 1; i >= 0; --i) {
            if ((1 << i) <= depth[b] - depth[a]) {
                b = pr[i][b];
            }
        }
        if (a == b) {
            return a;
        }
        for (int i = pr.length - 1; i >= 0; --i) {
            if (pr[i][a] != pr[i][b]) {
                a = pr[i][a];
                b = pr[i][b];
            }
        }
        return pr[0][a];
    }

    @Override
    public String getName() {
        return "jumpin lca";
    }
}
