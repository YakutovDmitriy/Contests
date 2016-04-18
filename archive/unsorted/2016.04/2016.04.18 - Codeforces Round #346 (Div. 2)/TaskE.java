package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.PairIntInt;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            graph[x].add(y);
            graph[y].add(x);
        }
        boolean[] used = new boolean[n];
        int[] queue = new int[n];
        int ans = 0;
        for (int x = 0; x < n; x++) {
            if (!used[x]) {
                PairIntInt cur = bfs(x, graph, used, queue);
                if (cur.first == cur.second + 1) {
                    ans++;
                }
            }
        }
        out.println(ans);
    }

    private PairIntInt bfs(int start, List<Integer>[] graph, boolean[] used, int[] queue) {
        int tail = 0;
        queue[tail++] = start;
        used[start] = true;
        int edges = 0;
        for (int i = 0; i < tail; i++) {
            int v = queue[i];
            for (int to : graph[v]) {
                edges++;
                if (!used[to]) {
                    used[to] = true;
                    queue[tail++] = to;
                }
            }
        }
        return new PairIntInt(tail, edges / 2);
    }
}
