package util.graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class DinicGraph {
    public final int n;
    public ArrayList<Edge>[] edges;

    public static final int INF = Integer.MAX_VALUE;

    public DinicGraph(int n) {
        this.n = n;
        edges = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            edges[i] = new ArrayList<>();
        }
    }

    public Edge addDirectedEdge(int from, int to, int capacity) {
        Edge e = new Edge(from, to, capacity);
        Edge r = new Edge(to, from, 0);
        e.rev = r;
        r.rev = e;
        edges[from].add(e);
        edges[to].add(r);
        return e;
    }

    public Edge addUndirectedEdge(int from, int to, int capacity) {
        Edge e = new Edge(from, to, capacity);
        Edge r = new Edge(to, from, capacity);
        e.rev = r;
        r.rev = e;
        edges[from].add(e);
        edges[to].add(r);
        return e;
    }

    private int dfsDinic(int v, int flow, int T, int[] d, int[] i) {
        if (flow == 0 || v == T) {
            return flow;
        }
        for (; i[v] < edges[v].size(); ++i[v]) {
            Edge e = edges[v].get(i[v]);
            int to = e.to;
            if (d[to] == d[v] + 1) {
                int now = dfsDinic(to, Math.min(flow, e.getResidualCapacity()), T, d, i);
                if (now != 0) {
                    e.flow += now;
                    e.rev.flow -= now;
                    return now;
                }
            }
        }
        return 0;
    }

    public void clear() {
        for (int v = 0; v < n; v++) {
            for (Edge e : edges[v]) {
                e.flow = 0;
            }
        }
    }

    public long getMaxFlow(int source, int target) {
        long flow = 0;
        int[] index = new int[n];
        int[] dist = new int[n];
        int[] queue = new int[n];
        while (true) {
            Arrays.fill(dist, INF);
            int head = 0;
            int tail = 0;
            queue[tail++] = source;
            dist[source] = 0;
            while (head < tail && dist[target] == INF) {
                int v = queue[head++];
                for (Edge e : edges[v]) {
                    if (dist[e.to] == INF && e.getResidualCapacity() > 0) {
                        dist[e.to] = dist[v] + 1;
                        queue[tail++] = e.to;
                    }
                }
            }
            if (dist[target] == INF) {
                break;
            }
            Arrays.fill(index, 0);
            int now = 1;
            while (now != 0) {
                now = dfsDinic(source, INF, target, dist, index);
                flow += now;
            }
        }
        return flow;
    }

    public int[] getMinCut(int source, int target) {
        getMaxFlow(source, target);
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        boolean[] was = new boolean[n];
        q[tail++] = source;
        was[source] = true;
        while (head < tail) {
            int v = q[head++];
            for (Edge e : edges[v]) {
                if (!was[e.to] && !e.isSaturated()) {
                    q[tail++] = e.to;
                    was[e.to] = true;
                }
            }
        }
        return Arrays.copyOf(q, tail);
    }

    public static class Edge {
        public final int from;
        public final int to;
        public final int capacity;
        public int flow;
        public Edge rev;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }

        public int getResidualCapacity() {
            return capacity - flow;
        }

        public boolean isSaturated() {
            return capacity == flow;
        }
    }
}