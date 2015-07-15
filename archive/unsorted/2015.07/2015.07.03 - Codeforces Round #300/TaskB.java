package code;

import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] a = new int[10];
        int size = 0;
        while (n > 0) {
            a[size++] = n % 10;
            n /= 10;
        }
        a = Arrays.copyOf(a, size);
        int ans = 0;
        for (int i : a) {
            ans = Math.max(i, ans);
        }
        int[] t = new int[ans];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < ans; j++) {
                t[j] *= 10;
            }
            for (int j = 0; j < a[i]; j++) {
                t[j]++;
            }
        }
        out.println(ans);
        out.printArray(t);
    }
}
