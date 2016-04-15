package code;

import util.FastReader;
import util.FastWriter;

import java.util.*;

public class TaskD {

    class Edge {
        int to;
        int index;

        public Edge(int to, int index) {
            this.to = to;
            this.index = index;
        }
    }

    final int MAX = 200_200;
    final int size = 2 * MAX;
    final int left = MAX - 1;
    final int right = 2 * MAX - 1;

    List<Edge>[] edges;
    char[] color;
    int[] last;

    void dfs(int v, char now) {
        for (; last[v] < edges[v].size(); last[v]++) {
            Edge edge = edges[v].get(last[v]);
            if (color[edge.index] == 0) {
                color[edge.index] = now;
                dfs(edge.to, now == 'b' ? 'r' : 'b');
            }
        }
    }

    public void solve(int testNumber, FastReader in, FastWriter out) {
        edges = new List[size];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
        int n = in.nextInt();
        color = new char[n + 1 + size];
        int indexCount = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt() + MAX;
            edges[x].add(new Edge(y, indexCount));
            edges[y].add(new Edge(x, indexCount));
            indexCount++;
        }
        last = new int[size];
        for (int i = 0; i < MAX; i++) {
            if (edges[i].size() % 2 == 1) {
                edges[i].add(new Edge(right, indexCount));
                edges[right].add(new Edge(i, indexCount));
                indexCount++;
            }
        }
        for (int i = MAX; i < size; i++) {
            if (edges[i].size() % 2 == 1) {
                edges[i].add(new Edge(left, indexCount));
                edges[left].add(new Edge(i, indexCount));
                indexCount++;
            }
        }
        for (int i = 0; i < size; i++) {
            dfs(i, 'r');
        }
        out.println(Arrays.copyOf(color, n));
    }
}
