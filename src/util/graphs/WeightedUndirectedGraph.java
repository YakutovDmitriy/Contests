package util.graphs;

import java.util.ArrayList;
import java.util.List;

public class WeightedUndirectedGraph {

    public int n;
    public List<Edge>[] edges;

    public static class Edge {
        public final int from;
        public final int to;
        public final int weight;
        Edge rev;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public WeightedUndirectedGraph(int n) {
        this.n = n;
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public Edge addEdge(int from, int to, int weight) {
        Edge e = new Edge(from, to, weight);
        Edge r = new Edge(to, from, weight);
        e.rev = r;
        r.rev = e;
        edges[from].add(e);
        edges[to].add(r);
        return e;
    }
}
