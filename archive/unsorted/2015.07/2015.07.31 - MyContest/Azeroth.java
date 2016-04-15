package code;

import util.FastReader;
import util.FastWriter;
import util.math.GeometryUtils;
import util.math.Point2DDouble;

import java.io.FileNotFoundException;

public class Azeroth {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        Point2DDouble[] a = new Point2DDouble[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Point2DDouble(in.nextInt(), in.nextInt());
        }
        Point2DDouble[] h = GeometryUtils.convexHull(a);
        for (Point2DDouble p : h) {
            out.println((int) p.x + " " + (int) p.y);
        }
    }
}
