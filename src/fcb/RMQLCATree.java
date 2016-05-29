package fcb;

import util.dataStructures.DSU;
import util.dataStructures.pairs.PairIntInt;

import java.util.ArrayList;
import java.util.List;

public class RMQLCATree implements LCATree {

    RangeMinimumQuery rmq;
    int[] verticeOnPos;
    int[] firstPosition;

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
        int[][] adjacents = new int[n][];
        {
            int[] deg = new int[n];
            for (PairIntInt edge : edges) {
                deg[edge.first]++;
                deg[edge.second]++;
            }
            for (int i = 0; i < n; i++) {
                adjacents[i] = new int[deg[i]];
                deg[i] = 0;
            }
            for (PairIntInt edge : edges) {
                int a = edge.first;
                int b = edge.second;
                adjacents[a][deg[a]++] = b;
                adjacents[b][deg[b]++] = a;
            }
        }
        buildTree(adjacents, root);
    }

    public void buildTree(int[][] adjacents, int root) {
        int n = adjacents.length;
        verticeOnPos = new int[n + n + 1];
        int curPos = 0;
        int[] depth = new int[n];
        firstPosition = new int[n];
        {
            int[] vs = new int[n + n + 1];
            int[] ancs = new int[n + n + 1];
            int sz = 0;
            vs[sz] = root;
            ancs[sz++] = root;
            while (sz > 0) {
                int v = vs[--sz];
                int anc = ancs[sz];
                if (anc >= 0) {
                    firstPosition[v] = curPos;
                    verticeOnPos[curPos++] = v;
                    for (int to : adjacents[v]) {
                        if (to != anc && to >= 0) {
                            depth[to] = depth[v] + 1;
                            vs[sz] = v;
                            ancs[sz++] = -1;
                            vs[sz] = to;
                            ancs[sz++] = v;
                        }
                    }
                } else {
                    verticeOnPos[curPos++] = v;
                }
            }
        }
        int[] toRmq = new int[curPos];
        for (int i = 0; i < toRmq.length; i++) {
            toRmq[i] = depth[verticeOnPos[i]];
        }
        rmq = new FarachColtonBenderRMQpm1();
        rmq.buildRMQ(toRmq);
    }

    @Override
    public int getLCA(int a, int b) {
        int x = firstPosition[a];
        int y = firstPosition[b];
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }
        y++;
        return verticeOnPos[rmq.getMinPos(x, y)];
    }

    @Override
    public String getName() {
        return "farach lca";
    }
}
