package util.dataStructures.segments;

public class SparseTableMin {

    private int[][] a;
    private int[] deg;

    public SparseTableMin(int[] array) {
        int n = array.length;
        int log = 1;
        while ((1 << (log - 1)) < n) {
            log++;
        }
        a = new int[log][n];
        System.arraycopy(array, 0, a[0], 0, n);
        for (int i = 1; i < log; i++) {
            int len = 1 << i;
            int half = len >> 1;
            for (int j = 0; j + len <= n; j++) {
                a[i][j] = Math.min(a[i - 1][j], a[i - 1][j + half]);
            }
        }
        deg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            deg[i] = 1 + deg[i >> 1];
        }
    }

    public int getMin(int left, int right) {
        int i = deg[right - left];
        return Math.min(a[i][left], a[i][right - (1 << i)]);
    }

    public int size() {
        return a[0].length;
    }
}