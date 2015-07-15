package code;

import util.FastReader;
import util.math.GeometryUtils;
import util.math.Point2DDouble;

import java.io.PrintWriter;
import java.util.Locale;

public class TaskD {

    boolean equals(double x, double y) {
        return Math.abs(x - y) < GeometryUtils.EPS;
    }

    void solveCase(FastReader in, PrintWriter out, Locale locale) {
        Point2DDouble[] M = new Point2DDouble[] {
                new Point2DDouble(in.nextInt(), in.nextInt()),
                new Point2DDouble(in.nextInt(), in.nextInt()),
                new Point2DDouble(in.nextInt(), in.nextInt())
        };

        for (int i = 0; i < 6; i++) {
            Point2DDouble X = M[0];
            Point2DDouble Y = M[1];
            Point2DDouble Z = M[2];

            Point2DDouble S = Z.symmetryTo(Y);

            if (Math.abs(X.subtract(Y).mulv(S.subtract(Y))) > GeometryUtils.EPS) {

                Point2DDouble B = Point2DDouble.getCenter(X, Y, S);
                Point2DDouble A = B.symmetryTo(X);
                Point2DDouble C = B.symmetryTo(Y);
                Point2DDouble D = C.symmetryTo(Z);

                boolean good = GeometryUtils.convexHull(new Point2DDouble[] {A, B, C, D}).length == 4;
                good &= equals(A.dist(B), B.dist(C));
                good &= equals(A.dist(B), C.dist(D));

                if (good) {
                    out.println("YES");
                    out.printf(locale, "%.10f %.10f ", A.x, A.y);
                    out.printf(locale, "%.10f %.10f ", B.x, B.y);
                    out.printf(locale, "%.10f %.10f ", C.x, C.y);
                    out.printf(locale, "%.10f %.10f", D.x, D.y);
                    out.println();
                    return;
                }
            }

            if (i % 2 == 0) {
                Point2DDouble T = M[0];
                M[0] = M[1];
                M[0] = T;
            } else {
                Point2DDouble T = M[2];
                M[2] = M[0];
                M[0] = T;
            }
        }
        out.println("NO");
        out.println();
    }

    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int t = in.nextInt();
        Locale locale = new Locale("US");
        for (int i = 0; i < t; i++) {
            solveCase(in, out, locale);
        }
    }
}
