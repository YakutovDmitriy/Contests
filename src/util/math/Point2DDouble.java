package util.math;

public class Point2DDouble {

    public double x;
    public double y;

    public Point2DDouble() {
        x = 0;
        y = 0;
    }

    public Point2DDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2DDouble add(Point2DDouble a) {
        return new Point2DDouble(x + a.x, y + a.y);
    }

    public Point2DDouble subtract(Point2DDouble a) {
        return new Point2DDouble(x - a.x, y - a.y);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double length2() {
        return x * x + y * y;
    }

    public double muls(Point2DDouble a) {
        return x * a.x + y * a.y;
    }

    public double mulv(Point2DDouble a) {
        return x * a.y - y * a.x;
    }

    public Point2DDouble multiply(double k) {
        return new Point2DDouble(x * k, y * k);
    }

    public Point2DDouble divide(double k) {
        return new Point2DDouble(x / k, y / k);
    }

    public Point2DDouble rotate(double cos, double sin) {
        return new Point2DDouble(
                x * cos - y * sin,
                x * sin + y * cos
        );
    }

    public Point2DDouble rotate() {
        return new Point2DDouble(-y, x);
    }

    public double dist(Point2DDouble a) {
        return subtract(a).length();
    }

    public Point2DDouble symmetryTo(Point2DDouble center) {
        return center.multiply(2).subtract(this);
    }

}
