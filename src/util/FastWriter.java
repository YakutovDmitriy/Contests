package util;

import java.io.*;
import java.util.Locale;

public class FastWriter extends PrintWriter {

    public PrintWriter printf(String format, Object ... args) {
        return super.printf(Locale.US, format, args);
    }

    public void printArray(int[] a) {
        if (a != null) {
            for (int i = 0; i < a.length; i++) {
                if (i > 0) print(' ');
                print(a[i]);
            }
        }
        println();
    }

    public void printArray(int[] a, int from, int to) {
        if (a != null) {
            for (int i = from; i < to; i++) {
                if (i > 0) print(' ');
                print(a[i]);
            }
        }
        println();
    }

    public void printArray(long[] a) {
        if (a != null) {
            for (int i = 0; i < a.length; i++) {
                if (i > 0) print(' ');
                print(a[i]);
            }
        }
        println();
    }

    public void printArray(long[] a, int from, int to) {
        if (a != null) {
            for (int i = from; i < to; i++) {
                if (i > 0) print(' ');
                print(a[i]);
            }
        }
        println();
    }

    public <T> void printArray(T[] a) {
        if (a != null) {
            for (int i = 0; i < a.length; i++) {
                if (i > 0) print(' ');
                print(a[i]);
            }
        }
        println();
    }

    public <T> void printArray(T[] a, int from, int to) {
        if (a != null) {
            for (int i = from; i < to; i++) {
                if (i > 0) print(' ');
                print(a[i]);
            }
        }
        println();
    }

    public void printArray(double[] a, int precision) {
        if (a != null) {
            String format = "%." + precision + "f";
            for (int i = 0; i < a.length; i++) {
                if (i > 0) print(' ');
                printf(Locale.US, format, a[i]);
            }
        }
        println();
    }

    public void printArray(double[] a, int from, int to, int precision) {
        if (a != null) {
            String format = "%." + precision + "f";
            for (int i = from; i < to; i++) {
                if (i > 0) print(' ');
                printf(Locale.US, format, a[i]);
            }
        }
        println();
    }

    public void printArray(Locale locale, double[] a, int precision) {
        if (a != null) {
            String format = "%." + precision + "f";
            for (int i = 0; i < a.length; i++) {
                if (i > 0) print(' ');
                printf(locale, format, a[i]);
            }
        }
        println();
    }

    public void printArray(Locale locale, double[] a, int from, int to, int precision) {
        if (a != null) {
            String format = "%." + precision + "f";
            for (int i = from; i < to; i++) {
                if (i > 0) print(' ');
                printf(locale, format, a[i]);
            }
        }
        println();
    }

    public FastWriter() {
        super(System.out);
    }

    public FastWriter(Writer out) {
        super(out);
    }

    public FastWriter(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public FastWriter(OutputStream out) {
        super(out);
    }

    public FastWriter(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public FastWriter(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public FastWriter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public FastWriter(File file) throws FileNotFoundException {
        super(file);
    }

    public FastWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }

    public void close() {
        super.close();
    }

}
