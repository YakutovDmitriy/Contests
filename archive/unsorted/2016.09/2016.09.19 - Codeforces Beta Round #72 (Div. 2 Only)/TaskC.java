package code;

import util.AlgoUtils;
import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        Circle[] circles = new Circle[n];
        for (int i = 0; i < n; i++) {
            circles[i] = new Circle(in.nextInt(), in.nextInt(), i);
        }
        ArrayUtils.sort(circles);
        int m = in.nextInt();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int count = 0;
        for (int it = 0; it < m; it++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int i = AlgoUtils.lowerBound(circles, new Circle(x, -1, -1));
            for (int j = i - 3; j <= i + 3; j++) {
                if (j >= 0 && j < n) {
                    int index = circles[j].index;
                    if (ans[index] < 0 && circles[j].contains(x, y)){
                        ans[index] = it + 1;
                        count++;
                    }
                }
            }
        }
        out.println(count);
        out.printArray(ans);
    }

    private class Circle implements Comparable<Circle> {
        int x;
        int r;
        int index;

        public Circle(int x, int r, int index) {
            this.x = x;
            this.r = r;
            this.index = index;
        }

        @Override
        public int compareTo(Circle o) {
            return Integer.compare(x, o.x);
        }

        public boolean contains(int x, int y) {
            x -= this.x;
            return x * x + y * y <= r * r;
        }
    }
}
