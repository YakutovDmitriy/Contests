package util.graphs;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph {
    public int n;
    public List<Edge>[] edges;

    public static class Edge {
        public final int from;
        public final int to;

        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public DirectedGraph(int n) {
        this.n = n;
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public Edge addEdge(int from, int to) {
        Edge e = new Edge(from, to);
        edges[from].add(e);
        return e;
    }
}