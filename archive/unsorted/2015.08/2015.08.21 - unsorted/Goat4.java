package code;

import util.FastReader;
import util.FastWriter;
import util.math.GeometryUtils;
import util.math.Line2DDouble;
import util.math.Point2DDouble;

public class Goat4 {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        Point2DDouble a = new Point2DDouble(in.nextInt(), in.nextInt());
        Point2DDouble b = new Point2DDouble(in.nextInt(), in.nextInt());
        Point2DDouble c = new Point2DDouble(in.nextInt(), in.nextInt());
        Line2DDouble x = new Line2DDouble(b, b.add(bisector(a.subtract(b), c.subtract(b))));
        Line2DDouble y = new Line2DDouble(c, c.add(bisector(a.subtract(c), b.subtract(c))));
        Point2DDouble o = x.intersect(y);
        out.printf("%.15f %.15f\n", o.x, o.y);
    }

    Point2DDouble bisector(Point2DDouble a, Point2DDouble b) {
        double alen = a.length();
        double blen = b.length();
        a = a.multiply(blen);
        b = b.multiply(alen);
        return a.add(b);
    }
}
