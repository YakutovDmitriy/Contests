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

    public FastWriter printf(String format, Object ... args) {
        super.format(Locale.US, format, args);
        return this;
    }

    public void close() {
        super.close();
    }

    public FastWriter printArray(int[] a) {
        if (a != null) {
            for (int i = 0; i < a.length; i++) {
                if (i > 0) write(' ');
                write(String.valueOf(a[i]));
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }

    public FastWriter printCollection(Collection a) {
        if (a != null) {
            boolean was = false;
            for (Object x : a) {
                if (was) write(' ');
                write(String.valueOf(x));
                was = true;
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }

    public FastWriter printCollection(Collection a, int from, int to) {
        if (a == null) {
            println("null");
            return this;
        }
        if (from < 0 || to > a.size() || to <= from) {
            throw new IndexOutOfBoundsException("Range from " + from + " to " + to + " is incorrect");
        }
        int was = 0;
        for (Object x : a) {
            if (was >= from) {
                if (was > from) {
                    print(' ');
                }
                print(x);
            }
            ++was;
            if (was >= to) {
                break;
            }
        }
        println();
        return this;
    }

    public FastWriter printArray(int[] a, int from, int to) {
        if (a != null) {
            for (int i = from; i < to; i++) {
                if (i > from) write(' ');
                write(String.valueOf(a[i]));
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }

    public FastWriter printArray(long[] a) {
        if (a != null) {
            for (int i = 0; i < a.length; i++) {
                if (i > 0) write(' ');
                write(String.valueOf(a[i]));
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }

    public FastWriter printArray(long[] a, int from, int to) {
        if (a != null) {
            for (int i = from; i < to; i++) {
                if (i > from) write(' ');
                write(String.valueOf(a[i]));
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }

    public <T> FastWriter printArray(T[] a) {
        if (a != null) {
            for (int i = 0; i < a.length; i++) {
                if (i > 0) write(' ');
                write(String.valueOf(a[i]));
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }

    public <T> FastWriter printArray(T[] a, int from, int to) {
        if (a != null) {
            for (int i = from; i < to; i++) {
                if (i > from) write(' ');
                write(String.valueOf(a[i]));
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }

    public FastWriter printArray(double[] a, int precision) {
        if (a != null) {
            String f = "%." + precision + "f";
            for (int i = 0; i < a.length; i++) {
                if (i > 0) write(' ');
                format(Locale.US, f, a[i]);
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }

    public FastWriter printArray(double[] a, int from, int to, int precision) {
        if (a != null) {
            String f = "%." + precision + "f";
            for (int i = from; i < to; i++) {
                if (i > from) write(' ');
                format(Locale.US, f, a[i]);
            }
            println();
        } else {
            write("null");
            println();
        }
        return this;
    }
}
