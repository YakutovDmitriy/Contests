package code;

import util.FastReader;
import util.FastWriter;

public class TaskA {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int min1 = in.nextInt();
        int max1 = in.nextInt();
        int min2 = in.nextInt();
        int max2 = in.nextInt();
        int min3 = in.nextInt();
        int max3 = in.nextInt();
        for (int x = max1; x >= min1; x--) {
            int k = n - x;
            if (min2 + min3 > k || max2 + max3 < k) {
                continue;
            }
            int y = Math.min(max2, k - min3);
            int z = k - y;
            out.println(x + " " + y + " " + z);
            return;
        }
    }
}
