package util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AlgoUtils {

    public static void toGauss(double[][] a, final double EPS) {
        if (a == null || a[0] == null) {
            return;
        }
        int n = a.length;
        int m = a[0].length;
        for (int i = 0; i < n; i++) {
            int str = i;
            while (str < n && Math.abs(a[str][i]) < EPS) {
                str++;
            }
            if (str == n) {
                continue;
            }
            double div = a[str][i];
            for (int j = i; j < m; j++) {
                double temp = a[str][j];
                a[str][j] = a[i][j];
                a[i][j] = temp / div;
            }
            for (int ii = i + 1; ii < n; ii++) {
                double mul = a[ii][i];
                for (int j = i; j < m; j++) {
                    a[ii][j] -= a[i][j] * mul;
                }
            }
        }
        for (int i = n - 1; i > 0; i--) {
            if (Math.abs(a[i][i]) > EPS) {
                double div = a[i][i];
                for (int j = 0; j < m; j++) {
                    a[i][j] /= div;
                }
                for (int ii = i - 1; ii >= 0; ii--) {
                    double mul = a[ii][i];
                    for (int j = 0; j < m; j++) {
                        a[ii][j] -= mul * a[i][j];
                    }
                }
            }
        }
    }

    public static void toGauss(double[][] a, double[] values, final double EPS) {
        if (a == null || a[0] == null) {
            return;
        }
        int n = a.length;
        int m = a[0].length;
        for (int row = 0, col = 0; col < m && row < n; row++) {
            int str = row;
            for (int ii = row; ii < n; ii++) {
                if (Math.abs(a[ii][col]) > Math.abs(a[str][col])) {
                    str = ii;
                }
            }
            if (Math.abs(a[str][col]) < EPS) {
                continue;
            }
            double div = a[str][col];
            double temp = values[str];
            values[str] = values[row];
            values[row] = temp / div;
            for (int j = col; j < m; j++) {
                temp = a[str][j];
                a[str][j] = a[row][j];
                a[row][j] = temp / div;
            }
            for (int ii = row + 1; ii < n; ii++) {
                double mul = a[ii][col];
                for (int j = col; j < m; j++) {
                    a[ii][j] -= a[row][j] * mul;
                }
                values[ii] -= values[row] * mul;
            }
        }
        for (int i = n - 1; i > 0; i--) {
            if (Math.abs(a[i][i]) > EPS) {
                double div = a[i][i];
                values[i] /= div;
                for (int j = 0; j < m; j++) {
                    a[i][j] /= div;
                }
                for (int ii = i - 1; ii >= 0; ii--) {
                    double mul = a[ii][i];
                    for (int j = 0; j < m; j++) {
                        a[ii][j] -= mul * a[i][j];
                    }
                    values[ii] -= values[i] * mul;
                }
            }
        }
    }

    public static int josephProblem(int n, int k) {
        int ret = 0;
        for (int i = 1; i <= n; ++i) {
            ret = (ret + k) % i;
        }
        return ret + 1;
    }

    public static long maxSegmentSum(int[] a) {
        long res = Long.MIN_VALUE;
        long pref = 0;
        long min = 0;
        for (int x : a) {
            pref += x;
            res = Math.max(res, pref - min);
            min = Math.min(min, pref);
        }
        return res;
    }

    public static long minSegmentSum(int[] a) {
        long res = Long.MAX_VALUE;
        long pref = 0;
        long max = 0;
        for (int x : a) {
            pref += x;
            res = Math.min(res, pref - max);
            max = Math.max(max, pref);
        }
        return res;
    }

    public static int longestIncreasingSubsequence(int[] a) {
        int[] d = new int[a.length + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = Integer.MIN_VALUE;
        for (int x : a) {
            int j = upperBound(d, x);
            if (j < d.length && d[j - 1] < x && x < d[j]) {
                d[j] = x;
            }
        }
        int ret = a.length;
        while (d[ret] == Integer.MAX_VALUE) {
            ret--;
        }
        return ret;
    }

    public static int longestIncreasingSubsequence(long[] a) {
        long[] d = new long[a.length + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = Integer.MIN_VALUE;
        for (long x : a) {
            int j = upperBound(d, x);
            if (j < d.length && d[j - 1] < x && x < d[j]) {
                d[j] = x;
            }
        }
        int ret = a.length;
        while (d[ret] == Integer.MAX_VALUE) {
            ret--;
        }
        return ret;
    }

    public static int lowerBound(int[] a, int value) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] >= value) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static int lowerBound(long[] a, long value) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] >= value) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static int lowerBound(double[] a, double value) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] >= value) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static <T extends Comparable<? super T>> int lowerBound(T[] a, T value) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m].compareTo(value) >= 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static <T> int lowerBound(T[] a, T value, Comparator<? super T> comp) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (comp.compare(a[m], value) >= 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static <T extends Comparable<? super T>> int lowerBound(List<T> a, T value) {
        int l = -1;
        int r = a.size();
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a.get(m).compareTo(value) >= 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static <T> int lowerBound(List<T> a, T value, Comparator<? super T> comp) {
        int l = -1;
        int r = a.size();
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (comp.compare(a.get(m), value) >= 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static int upperBound(int[] a, int value) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] > value) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static int upperBound(long[] a, long value) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] > value) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static int upperBound(double[] a, double value) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] > value) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static <T extends Comparable<? super T>> int upperBound(T[] a, T value) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m].compareTo(value) > 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static <T> int upperBound(T[] a, T value, Comparator<? super T> comp) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (comp.compare(a[m], value) > 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static <T extends Comparable<? super T>> int upperBound(List<T> a, T value) {
        int l = -1;
        int r = a.size();
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a.get(m).compareTo(value) > 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static <T> int upperBound(List<T> a, T value, Comparator<? super T> comp) {
        int l = -1;
        int r = a.size();
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (comp.compare(a.get(m), value) > 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static int minimalExcluded(int[] a) {
        boolean[] has = new boolean[a.length];
        for (int x : a) {
            if (x < a.length) {
                has[x] = true;
            }
        }
        int ret = 0;
        while (ret < a.length && has[ret]) {
            ret++;
        }
        return ret;
    }

}