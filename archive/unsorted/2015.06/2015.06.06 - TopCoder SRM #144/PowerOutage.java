package code;

import java.util.ArrayList;

public class PowerOutage {

    ArrayList<Integer>[] to = new ArrayList[60];
    ArrayList<Integer>[] len = new ArrayList[60];
    int[] full = new int[60];
    int[] dist = new int[60];
    {
        for (int i = 0; i < 60; i++) {
            to[i] = new ArrayList<>();
            len[i] = new ArrayList<>();
        }
    }

    public int estimateTimeOut(int[] a, int[] b, int[] l) {
        for (int i = 0; i < a.length; i++) {
            to[a[i]].add(b[i]);
            len[a[i]].add(l[i]);
            to[b[i]].add(a[i]);
            len[b[i]].add(l[i]);
        }
        dfs(0, -1, 0);
        int max = 0;
        for (int i = 0; i < 60; i++) {
            max = Math.max(max, dist[i]);
        }
        return full[0] - max;
    }

    int dfs(int v, int prev, int now) {
        int sum = 0;
        dist[v] = now;
        for (int i = 0; i < to[v].size(); i++) {
            if (to[v].get(i) != prev) {
                sum += dfs(to[v].get(i), v, now + len[v].get(i)) + 2 * len[v].get(i);
            }
        }
        return full[v] = sum;
    }
}
