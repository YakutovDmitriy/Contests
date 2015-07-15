package util.graphs;

import util.dataStructures.PairLongInt;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

    public static final long INF = Long.MAX_VALUE - 100L * Integer.MAX_VALUE;

    public final int n;
    public long[] distance;
    public int[] previous;
    public WeightedUndirectedGraph graph;

    public Dijkstra(WeightedUndirectedGraph graph) {
        this.graph = graph;
        n = graph.n;
    }

    public void calcElogV(int start) {
        distance = new long[n];
        previous = new int[n];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        Arrays.fill(previous, -1);
        PriorityQueue<PairLongInt> queue = new PriorityQueue<>();
        queue.add(new PairLongInt(-distance[start], start));
        while (!queue.isEmpty()) {
            PairLongInt pair = queue.poll();
            int v = pair.second;
            if (distance[v] != -pair.first) {
                continue;
            }
            for (WeightedUndirectedGraph.Edge edge : graph.edges[v]) {
                if (distance[edge.to] > distance[v] + edge.weight) {
                    distance[edge.to] = distance[v] + edge.weight;
                    previous[edge.to] = v;
                    queue.add(new PairLongInt(-distance[edge.to], edge.to));
                }
            }
        }
    }

    public void calcV2(int start) {
        distance = new long[n];
        previous = new int[n];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        Arrays.fill(previous, -1);
        boolean[] used = new boolean[n];
        for (int it = 0; it < n; ++it) {
            int v = -1;
            for (int i = 0; i < n; i++) {
                if (!used[i] && distance[v] != INF && (v == -1 || distance[v] > distance[i])) {
                    v = i;
                }
            }
            if (v == -1) {
                break;
            }
            used[v] = true;
            for (WeightedUndirectedGraph.Edge edge : graph.edges[v]) {
                if (distance[edge.to] > distance[v] + edge.weight) {
                    distance[edge.to] = distance[v] + edge.weight;
                    previous[edge.to] = v;
                }
            }
        }
    }
}
