package code;

import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;
import util.dataStructures.DSU;
import util.dataStructures.PairIntInt;

import java.util.Comparator;

public class TaskF {
    final int[] dx = {0, 0, -1, 1};
    final int[] dy = {1, -1, 0, 0};

    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long k = in.nextLong();
        int[][] a = in.nextArray2Int(n, m);
        PairIntInt[] cells = new PairIntInt[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i * m + j] = new PairIntInt(i, j);
            }
        }
        ArrayUtils.sort(cells, (o1, o2) -> Integer.compare(a[o1.first][o1.second], a[o2.first][o2.second]));
        ArrayUtils.reverse(cells);
        DSU dsu = new DSU(n * m);
        boolean[][] used = new boolean[n][m];
        for (PairIntInt cell : cells) {
            int x = cell.first;
            int y = cell.second;
            for (int t = 0; t < dx.length; t++) {
                int i = x + dx[t];
                int j = y + dy[t];
                if (i >= 0 && i < n && j >= 0 && j < m && used[i][j]) {
                    dsu.unite(i * m + j, x * m + y);
                }
            }
            int size = dsu.getSizeOf(x * m + y);
            if (k % a[x][y] == 0 && (long) a[x][y] * size >= k) {
                int need = (int) (k / a[x][y]);
                int[][] ans = new int[n][m];
                bfs(x, y, a[x][y], need, a, ans);
                out.println("YES");
                for (int[] line : ans) {
                    out.printArray(line);
                }
                return;
            }
            used[x][y] = true;
        }
        out.println("NO");
    }

    private void bfs(int x, int y, int value, int count, int[][] a, int[][] res) {
        int[] qx = new int[count];
        int[] qy = new int[count];
        int tail = 0;
        qx[tail] = x;
        qy[tail++] = y;
        a[x][y] = -1;
        for (int head = 0; head < count; head++) {
            x = qx[head];
            y = qy[head];
            res[x][y] = value;
            for (int t = 0; t < dx.length; t++) {
                int i = x + dx[t];
                int j = y + dy[t];
                if (i >= 0 && i < a.length && j >= 0 && j < a[0].length && a[i][j] >= value && tail < count) {
                    qx[tail] = i;
                    qy[tail++] = j;
                    a[i][j] = -1;
                }
            }
        }
    }

}
