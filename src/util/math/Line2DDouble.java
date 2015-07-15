package util.math;

public class Line2DDouble {

    public double a;
    public double b;
    public double c;

    public Line2DDouble(double a, double b, double c) {

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Line2DDouble(Point2DDouble a, Point2DDouble b) {
        this.a = a.y - b.y;
        this.b = b.x - a.x;
        this.c = -this.a * a.x - this.b * a.y;
    }

    public Point2DDouble intersect(Line2DDouble line) {
        Point2DDouble ret = new Point2DDouble();
        ret.x = (this.b * line.c - this.c * line.b);
        ret.x /= (this.a * line.b - this.b * line.a);
        ret.y = (this.a * line.c - this.c * line.a);
        ret.y /= (this.b * line.a - this.a * line.b);
        return ret;
    }

    public Point2DDouble norm() {
        return new Point2DDouble(a, b);
    }
}