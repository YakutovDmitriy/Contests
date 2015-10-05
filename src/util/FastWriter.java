package util;

import java.io.*;
import java.util.Collection;
import java.util.Locale;

public class FastWriter extends PrintWriter {

    public FastWriter(OutputStream out) {
        super(out);
    }

    public FastWriter(Writer out) {
        super(out);
    }

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

    public void printCollection(Collection a) {
        boolean was = false;
        for (Object x : a) {
            if (was) print(' ');
            print(x);
            was = true;
        }
        println();
    }

    public void printArray(int[] a, int from, int to) {
        if (a != null) {
            for (int i = from; i < to; i++) {
                if (i > from) print(' ');
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
                if (i > from) print(' ');
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
                if (i > from) print(' ');
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
                if (i > from) print(' ');
                printf(Locale.US, format, a[i]);
            }
        }
        println();
    }

    public void close() {
        super.close();
    }

}
