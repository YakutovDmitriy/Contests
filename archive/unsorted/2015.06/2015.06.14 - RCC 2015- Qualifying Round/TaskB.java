package code;

import util.FastReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskB {

    int[] team;
    List<Integer>[] edges;
    List<Integer>[] types;
    boolean[] used;

    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        team = new int[n];
        for (int i = 0; i < n; ++i) {
            team[i] = in.nextInt() - 1;
        }
        edges = new ArrayList[n];
        types = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            edges[i] = new ArrayList<>();
            types[i] = new ArrayList<>();
        }
        boolean bad = false;
        for (int i = 0; i < m; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int type = in.nextInt();
            edges[a].add(b);
            types[a].add(type);
            edges[b].add(a);
            types[b].add(type);
            if (team[a] != -1 && team[b] != -1) {
                if ((team[a] ^ type) != team[b]) {
                    bad = true;
                }
            }
        }
        if (n % 2 == 1 || bad) {
            out.println("NO");
            return;
        }
        int nextTeam = 2;
        used = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                if (team[i] == -1) {
                    team[i] = nextTeam;
                    nextTeam += 2;
                }
                dfs(i);
            }
        }
        int[] count = new int[nextTeam];
        for (int i = 0; i < n; ++i) {
            count[team[i]]++;
        }
        int[] diffs = new int[nextTeam / 2];
        for (int i = 0; i < nextTeam; i += 2) {
            diffs[i / 2] = count[i] - count[i + 1];
        }
        boolean[] mask = getMask(diffs);
        if (mask == null) {
            out.println("NO");
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (mask[team[i] / 2]) {
                team[i] ^= 1;
            }
            team[i] &= 1;
        }
        for (int v = 0; v < n; v++) {
            for (int i = 0; i < edges[v].size(); i++) {
                int to = edges[v].get(i);
                int type = types[v].get(i);
                if ((team[to] ^ type) != team[v]) {
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            out.print(team[i] + 1);
        }
        out.println();
    }

    void dfs(int v) {
        used[v] = true;
        for (int i = 0; i < edges[v].size(); ++i) {
            int to = edges[v].get(i);
            int edge = types[v].get(i);
            if (!used[to]) {
                if (team[to] == -1) {
                    team[to] = team[v] ^ edge;
                }
                dfs(to);
            }
        }
    }

    boolean[] getMask(int[] a) {
        int n = a.length;
        boolean[] ret = new boolean[n];
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] < 0) {
                a[i] *= -1;
                ret[i] = true;
            }
            sum += a[i];
        }
        if (sum % 2 == 1) {
            return null;
        }
        boolean[][] can = new boolean[sum + 1][n + 1];
        boolean[][] take = new boolean[sum + 1][n + 1];
        can[0][0] = true;
        for (int i = 0; i < n; ++i) {
            for (int x = 0; x <= sum; x++) {
                if (can[x][i]) {
                    can[x][i + 1] = true;
                    can[x + a[i]][i + 1] = true;
                    take[x + a[i]][i + 1] = true;
                }
            }
        }
        int need = sum / 2;
        if (!can[need][n]) {
            return null;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (take[need][i + 1]) {
                ret[i] ^= true;
                need -= a[i];
            }
        }
        if (need > 0) {
            throw new AssertionError();
        }
        if (ret[0]) {
            for (int i = 0; i < n; i++) {
                ret[i] ^= true;
            }
        }
        return ret;
    }
}
