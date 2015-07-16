package util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AlgoUtils {

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