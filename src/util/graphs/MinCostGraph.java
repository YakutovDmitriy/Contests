package util.graphs;

import java.util.*;

public class MinCostGraph {
    public static int INF = Integer.MAX_VALUE / 5;
    public static long INFL = Long.MAX_VALUE / 5;

    public final int n;
    public List<Edge>[] edges;

    public MinCostGraph(int n) {
        this.n = n;
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public static class Edge {
        public int from;
        public int to;
        public int capacity;
        public int flow;
        public int cost;
        public Edge rev;

        public Edge(int from, int to, int capacity, int cost) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.cost = cost;
        }
    }

    public Edge addDirectedEdge(int from, int to, int capacity, int cost) {
        Edge e = new Edge(from, to, capacity, cost);
        Edge r = new Edge(to, from, 0, -cost);
        e.rev = r;
        r.rev = e;
        edges[from].add(e);
        edges[to].add(r);
        return e;
    }

    public Edge addUndirectedEdge(int from, int to, int capacity, int cost) {
        addDirectedEdge(to, from, capacity, cost);
        return addDirectedEdge(from, to, capacity, cost);
    }

    public void clear() {
        for (int i = 0; i < n; i++) {
            for (Edge e : edges[i]) {
                e.flow = 0;
            }
        }
    }

    public long[] getMinCostAndMaxFlow(int source, int target) {
        clear();
        Edge[] prev = new Edge[n];
        long[] dist = new long[n];
        int[] id = new int[n];
        long flow = 0;
        long cost = 0;
        while (true) {
            Arrays.fill(dist, INF);
            Arrays.fill(id, 0);
            dist[source] = 0;
            Deque<Integer> q = new ArrayDeque<>();
            q.addFirst(source);
            while (!q.isEmpty()) {
                int v = q.pollLast();
                id[v] = 2;
                for (Edge e : edges[v]) {
                    if (e.capacity > e.flow && dist[e.to] > dist[v] + e.cost) {
                        dist[e.to] = dist[v] + e.cost;
                        if (id[e.to] == 0) {
                            q.addFirst(e.to);
                        } else if (id[e.to] == 2) {
                            q.addLast(e.to);
                        }
                        prev[e.to] = e;
                        id[e.to] = 1;
                    }
                }
            }
            if (dist[target] == INF) {
                break;
            }
            long now = INFL;
            for (int v = target; v != source; v = prev[v].from) {
                now = Math.min(now, prev[v].capacity - prev[v].flow);
            }
            flow += now;
            for (int v = target; v != source; v = prev[v].from) {
                prev[v].flow += now;
                prev[v].rev.flow -= now;
                cost += (long) prev[v].cost * now;
            }
        }
        return new long[] {cost, flow};
    }
}