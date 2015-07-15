package code;

import util.FastReader;

import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class TaskE {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        Map<Integer, Integer> numberOnPos = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            Integer x = numberOnPos.get(a);
            if (x == null) {
                x = a;
            }
            Integer y = numberOnPos.get(b);
            if (y == null) {
                y = b;
            }
            numberOnPos.put(a, y);
            numberOnPos.put(b, x);
        }
        int[] position = new int[numberOnPos.size()];
        int[] number = new int[numberOnPos.size()];
        int size = 0;
        for (Integer x : numberOnPos.keySet()) {
            position[size] = x;
            number[size++] = numberOnPos.get(x);
        }
        long ans = getInversions(number.clone(), 0, number.length);
        for (int i = 0; i < size; i++) {
            int a = number[i];
            int b = position[i];
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }
            int l = getPos(position, a);
            int r = getPos(position, b);
            int count = r - l - 1;
            count = b - a - 1 - count;
            ans += count;
        }
        out.println(ans);
    }

    int getPos(int[] a, int x) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] <= x) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    }

    long getInversions(int[] a, int l, int r) {
        if (r - l <= 1) {
            return 0L;
        }
        int m = (l + r) / 2;
        long res = getInversions(a, l, m) + getInversions(a, m, r);
        int[] buf = new int[r - l];
        int size = 0;
        for (int i = l, j = m; i < m || j < r;) {
            if (i < m && (j == r || a[i] <= a[j])) {
                buf[size++] = a[i];
                i++;
            } else {
                buf[size++] = a[j];
                j++;
                res += m - i;
            }
        }
        for (int i = 0; i < size; i++) {
            a[i + l] = buf[i];
        }
        return res;
    }
}
