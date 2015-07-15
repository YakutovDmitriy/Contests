package util.dataStructures;

public class SparseTableMax {

    private int[][] a;
    private int[] deg;

    public SparseTableMax(int[] array) {
        int n = array.length;
        int log = 1;
        while ((1 << (log - 1)) < n) {
            log++;
        }
        a = new int[log][];
        a[0] = array.clone();
        for (int i = 1; i < log; i++) {
            int halfLen = 1 << (i - 1);
            a[i] = new int[n];
            for (int j = 0; j + halfLen < a[i].length; j++) {
                a[i][j] = Math.max(a[i - 1][j], a[i - 1][j + halfLen]);
            }
        }
        deg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            deg[i] = 1 + deg[i >> 1];
        }
    }

    public int getMax(int left, int right) {
        int i = deg[right - left];
        return Math.max(a[i][left], a[i][right - (1 << i)]);
    }
}