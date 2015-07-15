package util.graphs;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph {
    public int n;
    public List<Edge>[] edges;

    public static class Edge {
        public final int from;
        public final int to;
        Edge rev;

        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public UndirectedGraph(int n) {
        this.n = n;
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public Edge addEdge(int from, int to) {
        Edge e = new Edge(from, to);
        Edge r = new Edge(to, from);
        e.rev = r;
        r.rev = e;
        edges[from].add(e);
        edges[to].add(r);
        return e;
    }
}