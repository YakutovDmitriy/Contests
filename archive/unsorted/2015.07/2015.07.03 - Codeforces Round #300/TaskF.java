package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.BinaryIndexedTreeSum;

public class TaskF {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] a = in.nextArrayInt(n);
        int[] d = new int[n + 1];
        final int max = 450;
        for (int i = 1; i < n; i++) {
            for (int prev = 0; prev * max < i; prev++) {
                if (a[i] < a[prev]) {
                    int l = (i - 1) / (prev + 1) + 1;
                    int r = prev == 0 ? n - 1 : (i - 1) / prev;
                    d[l]++;
                    d[r + 1]--;
                }
            }
            for (int k = 1; ; k++) {
                int prev = (i - 1) / k;
                if (prev * max < i) {
                    break;
                }
                if (a[i] < a[prev]) {
                    d[k]++;
                    d[k + 1]--;
                }
            }
        }
        int[] sums = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            sums[i] = d[i + 1];
            if (i > 0) {
                sums[i] += sums[i - 1];
            }
        }
        out.printArray(sums);
    }
}
