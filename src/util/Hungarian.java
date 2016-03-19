package util;

import static java.util.Arrays.fill;

public class Hungarian {

    public final int size;
    public final int INF;
    public final int[][] a;

    public int[] px;
    public int[] py;
    public int[] dx;
    public int[] dy;

    public Hungarian(int[][] a, final int INF) {
        this.size = a.length;
        this.INF = INF;
        this.a = a;
        int n = a.length;
        px = new int[n];
        py = new int[n];
        dx = new int[n];
        dy = new int[n];
        boolean[] usedX = new boolean[n];
        boolean[] usedY = new boolean[n];
        int[] min = new int[n];
        int[] minPos = new int[n];
        int[] from = new int[n];
        fill(px, -1);
        fill(py, -1);
        for (int row = 0; row < n; row++) {
            int x1 = row;
            fill(usedX, false);
            fill(usedY, false);
            fill(min, INF);
            int x0 = -1;
            int y0 = -1;
            while (x1 >= 0) {
                usedX[x1] = true;
                int delta = INF;
                for (int y = 0; y < n; y++) {
                    int now = a[x1][y] + dx[x1] + dy[y];
                    if (now < min[y]) {
                        min[y] = now;
                        minPos[y] = x1;
                    }
                    if (!usedY[y] && min[y] < delta) {
                        delta = min[y];
                        x0 = minPos[y];
                        y0 = y;
                    }
                }
                for (int x = 0; x < n; x++) {
                    if (usedX[x]) {
                        dx[x] -= delta;
                    }
                }
                for (int y = 0; y < n; y++) {
                    if (usedY[y]) {
                        dy[y] += delta;
                    } else {
                        min[y] -= delta;
                    }
                }
                from[y0] = x0;
                usedY[y0] = true;
                x1 = py[y0];
            }
            while (y0 >= 0) {
                x0 = from[y0];
                int newy0 = px[x0];
                px[x0] = y0;
                py[y0] = x0;
                y0 = newy0;
            }
        }
    }

    public int size() {
        return size;
    }

    public long getSummaryCost() {
        long cost = 0;
        for (int i = 0; i < size; i++) {
            cost += a[i][px[i]];
        }
        return cost;
    }

}
