package util.math;

import java.util.Arrays;
import java.util.Comparator;

public abstract class GeometryUtils {

    public static final double EPS = 1e-8;

    public static Line2DDouble midPerpendicular(Point2DDouble a, Point2DDouble b) {
        Point2DDouble per = b.subtract(a).rotate();
        Point2DDouble middle = b.add(a).divide(2);
        return new Line2DDouble(middle, middle.add(per));
    }

    public static Point2DDouble getCenter(Point2DDouble a, Point2DDouble b, Point2DDouble c) {
        return midPerpendicular(a, b).intersect(midPerpendicular(b, c));
    }

    public static int signum(double x) {
        if (x < -EPS) {
            return -1;
        }
        if (x > EPS) {
            return 1;
        }
        return 0;
    }

    public static boolean equals(double a, double b) {
        return signum(a - b) == 0;
    }

    public static boolean isIn(Point2DDouble[] a, Point2DDouble p) {
        int n = a.length;
        int l = 1, r = n - 1;
        boolean ret = a[l].mulv(p) > -EPS && p.mulv(a[r]) > -EPS;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (p.mulv(a[m]) > EPS) {
                r = m;
            } else {
                l = m;
            }
        }
        ret &= a[r].subtract(a[l]).mulv(p.subtract(a[l])) > -EPS;
        ret &= p.subtract(a[r]).mulv(a[l].subtract(a[r])) > -EPS;
        return ret;
    }

    public static int lowerOfLeftmosts(Point2DDouble[] a) {
        int ret = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i].x < a[ret].x || equals(a[i].x, a[ret].x) && a[i].y < a[ret].y) {
                ret = i;
            }
        }
        return ret;
    }

    public static int lowerOfRightmosts(Point2DDouble[] a) {
        int ret = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i].x > a[ret].x || equals(a[i].x, a[ret].x) && a[i].y < a[ret].y) {
                ret = i;
            }
        }
        return ret;
    }

    public static Point2DDouble[] minkowskiAddition(Point2DDouble[] a, Point2DDouble[] b) {
        int n = a.length;
        int m = b.length;
        if (n == 0) {
            return b;
        }
        if (m == 0) {
            return a;
        }
        int p = lowerOfLeftmosts(a);
        int q = lowerOfLeftmosts(b);
        Point2DDouble[] x = new Point2DDouble[n];
        Point2DDouble[] y = new Point2DDouble[m];
        Point2DDouble[] z = new Point2DDouble[n + m];
        for (int it = 0; it < n; it++) {
            int i = (it + p) % n;
            int j = (i + 1) % n;
            x[it] = a[j].subtract(a[i]);
        }
        for (int it = 0; it < m; ++it) {
            int i = (it + q) % m;
            int j = (i + 1) % m;
            y[it] = b[j].subtract(b[i]);
        }
        int size = 0;
        for (int i = 0, j = 0; i < x.length || j < y.length; ) {
            if (i < x.length && (j < y.length || x[i].mulv(y[j]) > EPS)) {
                z[size] = x[i];
                i++;
            } else {
                z[size] = y[j];
                j++;
            }
            size++;
        }
        Point2DDouble[] ret = new Point2DDouble[n + m];
        ret[0] = a[p].add(b[q]);
        for (int i = 1; i < n + m; ++i) {
            ret[i] = ret[i - 1].add(z[i - 1]);
        }
        return ret;
    }

    public static Point2DDouble[] convexHull(Point2DDouble[] a) {
        int n = a.length;
        int mn = 0;
        for (int i = 1; i < n; i++) {
            if (a[i].x < a[mn].x || (a[i].x == a[mn].x && a[i].y < a[mn].y)) {
                mn = i;
            }
        }
        Point2DDouble start = a[mn];
        a[mn] = a[0];
        a[0] = start;
        for (int i = 1; i < n; i++) {
            a[i] = a[i].subtract(start);
        }
        Arrays.sort(a, 1, a.length, new Comparator<Point2DDouble>() {
            public int compare(Point2DDouble o1, Point2DDouble o2) {
                double m = o1.mulv(o2);
                if (signum(m) != 0) {
                    return -signum(m);
                }
                return signum(o1.length2() - o2.length2());
            }
        });
        for (int i = 1; i < n; i++) {
            a[i] = a[i].add(start);
        }
        Point2DDouble[] h = new Point2DDouble[n];
        int size = 0;
        for (Point2DDouble now : a) {
            while (size > 1 && h[size - 1].subtract(h[size - 2]).mulv(now.subtract(h[size - 2])) < EPS) {
                size--;
            }
            h[size++] = now;
        }
        return Arrays.copyOf(h, size);
    }
}