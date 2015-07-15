package util.graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class KuhnGraph {
    public int n;
    public int m;
    public ArrayList<Integer>[] edges;

    public KuhnGraph(int n, int m) {
        this.n = n;
        this.m = m;
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
    }

    private boolean dfsKuhn(int v, int[] used, int using, int[] next, int[] prev) {
        used[v] = using;
        for (Integer to : edges[v]) {
            if (prev[to] == -1 || (used[prev[to]] != using && dfsKuhn(prev[to], used, using, next, prev))) {
                next[v] = to;
                prev[to] = v;
                return true;
            }
        }
        return false;
    }

    public int[] findMatching() {
        int[] next = new int[n];
        Arrays.fill(next, -1);
        int[] prev = new int[m];
        Arrays.fill(prev, -1);
        int[] used = new int[n];
        int using = 0;
        while (true) {
            boolean was = false;
            using++;
            for (int v = 0; v < n; v++) {
                if (used[v] != using && next[v] == -1 && dfsKuhn(v, used, using, next, prev)) {
                    was = true;
                }
            }
            if (!was) {
                break;
            }
        }
        return next;
    }

    public int findMatchingSize() {
        int ret = 0;
        int[] next = new int[n];
        Arrays.fill(next, -1);
        int[] prev = new int[m];
        Arrays.fill(prev, -1);
        int[] used = new int[n];
        int using = 0;
        while (true) {
            boolean was = false;
            using++;
            for (int v = 0; v < n; v++) {
                if (used[v] != using && next[v] == -1 && dfsKuhn(v, used, using, next, prev)) {
                    ret++;
                    was = true;
                }
            }
            if (!was) {
                break;
            }
        }
        return ret;
    }

}