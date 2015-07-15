package code;

import util.FastReader;
import util.FastWriter;
import util.graphs.DirectedGraph;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        if (m == 0) {
            out.println("3 " + (long) n * (n - 1) * (n - 2) / 6);
            return;
        }
        UndirectedGraph g = new UndirectedGraph(n);
        for (int i = 0; i < m; i++) {
            g.addEdge(in.nextInt() - 1, in.nextInt() - 1);
        }
        int[] color = new int[n];
        Arrays.fill(color, -1);
        int last = 0;
        int[] queue = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                color[i] = last;
                last += 2;
                int head = 0;
                int tail = 0;
                queue[tail++] = i;
                while (head < tail) {
                    int v = queue[head++];
                    for (UndirectedGraph.Edge e : g.edges[v]) {
                        if (color[e.to] == -1) {
                            color[e.to] = color[v] ^ 1;
                            queue[tail++] = e.to;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (UndirectedGraph.Edge e : g.edges[i]) {
                if (color[i] == color[e.to]) {
                    out.println("0 1");
                    return;
                }
            }
        }
        int[] size = new int[last];
        for (int i = 0; i < n; i++) {
            size[color[i]]++;
        }
        boolean hasBig = false;
        for (int i = 0; i < last / 2; i++) {
            hasBig |= size[2 * i] + size[2 * i + 1] >= 3;
        }
        if (hasBig) {
            long ans = 0;
            for (int i = 0; i < last / 2; i++) {
                if (size[2 * i] + size[2 * i + 1] >= 3) {
                    ans += (long) size[2 * i] * (size[2 * i] - 1) / 2;
                    ans += (long) size[2 * i + 1] * (size[2 * i + 1] - 1) / 2;
                }
            }
            out.println("1 " + ans);
        } else {
            out.println("2 " + ((long) m * (n - 2)));
        }
    }
}
