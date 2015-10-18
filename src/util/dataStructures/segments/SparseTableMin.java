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
        a = new int[log][];
        a[0] = array.clone();
        for (int i = 1; i < log; i++) {
            int len = 1 << i;
            a[i] = new int[n];
            for (int j = 0; j + len <= a[i].length; j++) {
                a[i][j] = Math.min(a[i - 1][j], a[i - 1][j + (len >> 1)]);
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