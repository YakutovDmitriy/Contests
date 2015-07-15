package util.math;

import java.math.BigInteger;

public class Rational implements Comparable<Rational> {
    public BigInteger numerator;
    public BigInteger denominator;

    public Rational(int a) {
        numerator = new BigInteger(a + "");
        denominator = BigInteger.ONE;
    }

    public Rational(long a) {
        numerator = new BigInteger(a + "");
        denominator = BigInteger.ONE;
    }

    public Rational(BigInteger a) {
        numerator = a;
        denominator = BigInteger.ONE;
    }

    public Rational(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        norm();
    }

    public Rational(Rational that) {
        numerator = that.numerator.add(BigInteger.ZERO);
        denominator = that.denominator.add(BigInteger.ZERO);
    }

    private void norm() {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Division by zero");
        }
        if (denominator.compareTo(BigInteger.ZERO) < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }
        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);
    }

    public Rational add(Rational that) {
        return new Rational(
                this.numerator.multiply(that.denominator).add(this.denominator.multiply(that.numerator)),
                this.denominator.multiply(that.denominator)
        );
    }

    public Rational subtract(Rational that) {
        return new Rational(
                this.numerator.multiply(that.denominator).subtract(this.denominator.multiply(that.numerator)),
                this.denominator.multiply(that.denominator)
        );
    }

    public Rational multiply(Rational that) {
        return new Rational(
                numerator.multiply(that.numerator),
                denominator.multiply(that.denominator)
        );
    }

    public Rational divide(Rational that) {
        return new Rational(
                numerator.multiply(that.denominator),
                denominator.multiply(that.numerator)
        );
    }

    public Rational pow(int x) {
        Rational res = new Rational(BigInteger.ONE);
        Rational a = new Rational(this);
        for (; x > 0; x >>= 1) {
            if ((x & 1) == 1) {
                res = res.multiply(a);
            }
            a = a.multiply(a);
        }
        return res;
    }

    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        }
        return numerator.toString() + "/" + denominator.toString();
    }

    @Override
    public int compareTo(Rational o) {
        return this.subtract(o).numerator.signum();
    }
}
