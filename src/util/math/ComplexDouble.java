package util.math;

public class ComplexDouble extends Number {

    final double real;
    final double imaginary;

    public ComplexDouble(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexDouble(double real) {
        this(real, 0);
    }

    public ComplexDouble add(ComplexDouble c) {
        return new ComplexDouble(real + c.real, imaginary + c.imaginary);
    }

    public ComplexDouble add(double c) {
        return new ComplexDouble(real + c, imaginary);
    }

    public ComplexDouble subtract(ComplexDouble c) {
        return new ComplexDouble(real - c.real, imaginary - c.imaginary);
    }

    public ComplexDouble subtract(double c) {
        return new ComplexDouble(real - c, imaginary);
    }

    public ComplexDouble multiply(ComplexDouble c) {
        return new ComplexDouble(
                real * c.real - imaginary * c.imaginary,
                real * c.imaginary + imaginary * c.real
        );
    }

    public ComplexDouble multiply(double c) {
        return new ComplexDouble(real * c, imaginary * c);
    }

    public ComplexDouble divide(ComplexDouble c) {
        double denominator = c.getModuleSquare();
        return new ComplexDouble(
                (real * c.real + imaginary * c.imaginary) / denominator,
                (-real * c.imaginary + imaginary * c.real) / denominator
        );
    }

    public ComplexDouble divide(double c) {
        return new ComplexDouble(real / c, imaginary / c);
    }

    public double getModuleSquare() {
        return real * real + imaginary * imaginary;
    }

    public double module() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public ComplexDouble conjugate() {
        return new ComplexDouble(real, -imaginary);
    }

    public double re() {
        return real;
    }

    public double im() {
        return imaginary;
    }

    public int intValue() {
        return (int) real;
    }

    public long longValue() {
        return (long) real;
    }

    public float floatValue() {
        return (float) real;
    }

    public double doubleValue() {
        return real;
    }
}
