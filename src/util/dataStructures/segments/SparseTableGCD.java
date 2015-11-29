package util.dataStructures.segments;

import util.math.MathUtils;

public class SparseTableGCD {

    private int[][] a;
    private int[] deg;

    public SparseTableGCD(int[] array) {
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
                a[i][j] = MathUtils.gcd(a[i - 1][j], a[i - 1][j + half]);
            }
        }
        deg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            deg[i] = 1 + deg[i >> 1];
        }
    }

    public int getGCD(int left, int right) {
        int i = deg[right - left];
        return MathUtils.gcd(a[i][left], a[i][right - (1 << i)]);
    }
}