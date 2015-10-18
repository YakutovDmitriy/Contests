package util;

import java.util.*;

public class ArrayUtils {

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void sort(int[] a) {
        shuffle(a);
        Arrays.sort(a);
    }

    public static void sort(int[] a, int from, int to) {
        shuffle(a, from, to);
        Arrays.sort(a, from, to);
    }

    public static void sort(long[] a) {
        shuffle(a);
        Arrays.sort(a);
    }

    public static void sort(long[] a, int from, int to) {
        shuffle(a, from, to);
        Arrays.sort(a, from, to);
    }

    public static void sort(double[] a) {
        shuffle(a);
        Arrays.sort(a);
    }

    public static void sort(double[] a, int from, int to) {
        shuffle(a, from, to);
        Arrays.sort(a, from, to);
    }

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        shuffle(a);
        Arrays.sort(a);
    }

    public static <T extends Comparable<? super T>> void sort(T[] a, int from, int to) {
        shuffle(a, from, to);
        Arrays.sort(a, from, to);
    }

    public static <T> void sort(T[] a, Comparator<? super T> comparator) {
        shuffle(a);
        Arrays.sort(a, comparator);
    }

    public static <T> void sort(T[] a, int from, int to, Comparator<? super T> comparator) {
        shuffle(a, from, to);
        Arrays.sort(a, from, to, comparator);
    }

    public static void shuffle(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int j = i + RANDOM.nextInt(a.length - i);
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void shuffle(int[] a, int from, int to) {
        for (int i = from; i < to - 1; i++) {
            int j = i + RANDOM.nextInt(to - from - i);
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void shuffle(long[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int j = i + RANDOM.nextInt(a.length - i);
            long temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void shuffle(long[] a, int from, int to) {
        for (int i = from; i < to - 1; i++) {
            int j = i + RANDOM.nextInt(to - from - i);
            long temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void shuffle(double[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int j = i + RANDOM.nextInt(a.length - i);
            double temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void shuffle(double[] a, int from, int to) {
        for (int i = from; i < to - 1; i++) {
            int j = i + RANDOM.nextInt(to - from - i);
            double temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static <T> void shuffle(T[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int j = i + RANDOM.nextInt(a.length - i);
            T temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static <T> void shuffle(T[] a, int from, int to) {
        for (int i = from; i < to - 1; i++) {
            int j = i + RANDOM.nextInt(to - from - i);
            T temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static <T> T[] getNewArray(T[] instance, int length) {
        return (T[])java.lang.reflect.Array.newInstance
                (instance.getClass().getComponentType(), length);
    }

    public static int[] unique(int[] a) {
        ArrayList<Integer> res =  new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i == 0 || a[i] != a[i - 1]) {
                res.add(a[i]);
            }
        }
        return toIntArray(res);
    }

    public static long[] unique(long[] a) {
        ArrayList<Long> res =  new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i == 0 || a[i] != a[i - 1]) {
                res.add(a[i]);
            }
        }
        return toLongArray(res);
    }

    public static char[] unique(char[] a) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (i == 0 || a[i] != a[i - 1]) {
                res.append(a[i]);
            }
        }
        return res.toString().toCharArray();
    }

    public static double[] unique(double[] a) {
        ArrayList<Double> res =  new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i == 0 || a[i] != a[i - 1]) {
                res.add(a[i]);
            }
        }
        return toDoubleArray(res);
    }

    public static int[] toIntArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public static long[] toLongArray(List<Long> list) {
        long[] ret = new long[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public static double[] toDoubleArray(List<Double> list) {
        double[] ret = new double[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public static char[] toCharArray(List<Character> list) {
        char[] ret = new char[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public static void reverse(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static void reverse(long[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            long t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static void reverse(char[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            char t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static void reverse(double[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            double t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static <T> void reverse(T[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            T t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static void reverse(int[] a, int from, int to) {
        int len = to - from;
        for (int i = 0; i < len / 2; i++) {
            int t = a[from + i];
            a[from + i] = a[to - 1 - i];
            a[to - 1 - i] = t;
        }
    }

    public static void reverse(double[] a, int from, int to) {
        int len = to - from;
        for (int i = 0; i < len / 2; i++) {
            double t = a[from + i];
            a[from + i] = a[to - 1 - i];
            a[to - 1 - i] = t;
        }
    }

    public static void reverse(long[] a, int from, int to) {
        int len = to - from;
        for (int i = 0; i < len / 2; i++) {
            long t = a[from + i];
            a[from + i] = a[to - 1 - i];
            a[to - 1 - i] = t;
        }
    }

    public static void reverse(char[] a, int from, int to) {
        int len = to - from;
        for (int i = 0; i < len / 2; i++) {
            char t = a[from + i];
            a[from + i] = a[to - 1 - i];
            a[to - 1 - i] = t;
        }
    }

    public static <T> void reverse(T[] a, int from, int to) {
        int len = to - from;
        for (int i = 0; i < len / 2; i++) {
            T t = a[from + i];
            a[from + i] = a[to - 1 - i];
            a[to - 1 - i] = t;
        }
    }

    public static int[] reversed(int[] a) {
        int[] b = a.clone();
        reverse(b, 0, b.length);
        return b;
    }

    public static long[] reversed(long[] a) {
        long[] b = a.clone();
        reverse(b, 0, b.length);
        return b;
    }

    public static double[] reversed(double[] a) {
        double[] b = a.clone();
        reverse(b, 0, b.length);
        return b;
    }

    public static char[] reversed(char[] a) {
        char[] b = a.clone();
        reverse(b, 0, b.length);
        return b;
    }

    public static <T> T[] reversed(T[] a) {
        T[] b = a.clone();
        reverse(b, 0, b.length);
        return b;
    }

    public long getInversions(int[] array) {
        return getInversions(array.clone(), 0, array.length, new int[array.length]);
    }

    public long getInversions(int[] array, int from, int to, int[] buf) {
        if (to - from <= 1) {
            return 0L;
        }
        int middle = (from + to) / 2;
        long res = getInversions(array, from, middle, buf) + getInversions(array, middle, to, buf);
        int size = 0;
        for (int i = from, j = middle; i < middle || j < to;) {
            if (i < middle && (j == to || array[i] <= array[j])) {
                buf[size++] = array[i];
                i++;
            } else {
                buf[size++] = array[j];
                j++;
                res += middle - i;
            }
        }
        System.arraycopy(buf, 0, array, from, size);
        return res;
    }

    public long getInversions(long[] array) {
        return getInversions(array.clone(), 0, array.length, new long[array.length]);
    }

    public long getInversions(long[] array, int from, int to, long[] buf) {
        if (to - from <= 1) {
            return 0L;
        }
        int middle = (from + to) / 2;
        long res = getInversions(array, from, middle, buf) + getInversions(array, middle, to, buf);
        int size = 0;
        for (int i = from, j = middle; i < middle || j < to;) {
            if (i < middle && (j == to || array[i] <= array[j])) {
                buf[size++] = array[i];
                i++;
            } else {
                buf[size++] = array[j];
                j++;
                res += middle - i;
            }
        }
        System.arraycopy(buf, 0, array, from, size);
        return res;
    }

    public long getInversions(double[] array) {
        return getInversions(array.clone(), 0, array.length, new double[array.length]);
    }

    public long getInversions(double[] array, int from, int to, double[] buf) {
        if (to - from <= 1) {
            return 0L;
        }
        int middle = (from + to) / 2;
        long res = getInversions(array, from, middle, buf) + getInversions(array, middle, to, buf);
        int size = 0;
        for (int i = from, j = middle; i < middle || j < to;) {
            if (i < middle && (j == to || array[i] <= array[j])) {
                buf[size++] = array[i];
                i++;
            } else {
                buf[size++] = array[j];
                j++;
                res += middle - i;
            }
        }
        System.arraycopy(buf, 0, array, from, size);
        return res;
    }

    public long getInversions(char[] array) {
        return getInversions(array.clone(), 0, array.length, new char[array.length]);
    }

    public long getInversions(char[] array, int from, int to, char[] buf) {
        if (to - from <= 1) {
            return 0L;
        }
        int middle = (from + to) / 2;
        long res = getInversions(array, from, middle, buf) + getInversions(array, middle, to, buf);
        int size = 0;
        for (int i = from, j = middle; i < middle || j < to;) {
            if (i < middle && (j == to || array[i] <= array[j])) {
                buf[size++] = array[i];
                i++;
            } else {
                buf[size++] = array[j];
                j++;
                res += middle - i;
            }
        }
        System.arraycopy(buf, 0, array, from, size);
        return res;
    }

    public static <T extends Comparable<? super T>> long getInversions(T[] array, T[] buf) {
        return getInversions(array.clone(), 0, array.length, buf);
    }

    public static <T extends Comparable<? super T>> long getInversions(T[] array, int from, int to, T[] buf) {
        if (to - from <= 1) {
            return 0L;
        }
        int middle = (from + to) / 2;
        long res = getInversions(array, from, middle, buf) + getInversions(array, middle, to, buf);
        int size = 0;
        for (int i = from, j = middle; i < middle || j < to;) {
            if (i < middle && (j == to || array[i].compareTo(array[j]) <= 0)) {
                buf[size++] = array[i];
                i++;
            } else {
                buf[size++] = array[j];
                j++;
                res += middle - i;
            }
        }
        System.arraycopy(buf, 0, array, from, size);
        return res;
    }

    public static <T> long getInversions(T[] array, Comparator<? super T> comp, T[] buf) {
        return getInversions(array.clone(), 0, array.length, comp, buf);
    }

    public static <T> long getInversions(T[] array, int from, int to, Comparator<? super T> cmp, T[] buf) {
        if (to - from <= 1) {
            return 0L;
        }
        int middle = (from + to) / 2;
        long res = getInversions(array, from, middle, cmp, buf) + getInversions(array, middle, to, cmp, buf);
        int size = 0;
        for (int i = from, j = middle; i < middle || j < to;) {
            if (i < middle && (j == to || cmp.compare(array[i], array[j]) <= 0)) {
                buf[size++] = array[i];
                i++;
            } else {
                buf[size++] = array[j];
                j++;
                res += middle - i;
            }
        }
        System.arraycopy(buf, 0, array, from, size);
        return res;
    }

    public static char[][] rotateClockwise(char[][] s) {
        int n = s.length;
        int m = s[0].length;
        char[][] ret = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[j][n - i - 1] = s[i][j];
            }
        }
        return ret;
    }

    public static char[][] rotateCounterClockwise(char[][] s) {
        int n = s.length;
        int m = s[0].length;
        char[][] ret = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[m - j - 1][i] = s[i][j];
            }
        }
        return ret;
    }

    public static int[][] rotateClockwise(int[][] s) {
        int n = s.length;
        int m = s[0].length;
        int[][] ret = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[j][n - i - 1] = s[i][j];
            }
        }
        return ret;
    }

    public static int[][] rotateCounterClockwise(int[][] s) {
        int n = s.length;
        int m = s[0].length;
        int[][] ret = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[m - j - 1][i] = s[i][j];
            }
        }
        return ret;
    }

    public static boolean nextPermutation(int[] a) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                int now = i + 1;
                for (int j = i + 2; j < a.length; j++) {
                    if (a[j] > a[i] && a[j] < a[now]) {
                        now = j;
                    }
                }
                int t = a[now];
                a[now] = a[i];
                a[i] = t;
                reverse(a, i + 1, a.length);
                return true;
            }
        }
        return false;
    }

    public static boolean nextPermutation(long[] a) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                int now = i + 1;
                for (int j = i + 2; j < a.length; j++) {
                    if (a[j] > a[i] && a[j] < a[now]) {
                        now = j;
                    }
                }
                long t = a[now];
                a[now] = a[i];
                a[i] = t;
                reverse(a, i + 1, a.length);
                return true;
            }
        }
        return false;
    }

    public static boolean nextPermutation(double[] a) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                int now = i + 1;
                for (int j = i + 2; j < a.length; j++) {
                    if (a[j] > a[i] && a[j] < a[now]) {
                        now = j;
                    }
                }
                double t = a[now];
                a[now] = a[i];
                a[i] = t;
                reverse(a, i + 1, a.length);
                return true;
            }
        }
        return false;
    }

    public static boolean nextPermutation(char[] a) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                int now = i + 1;
                for (int j = i + 2; j < a.length; j++) {
                    if (a[j] > a[i] && a[j] < a[now]) {
                        now = j;
                    }
                }
                char t = a[now];
                a[now] = a[i];
                a[i] = t;
                reverse(a, i + 1, a.length);
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<? super T>> boolean nextPermutation(T[] a) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i].compareTo(a[i + 1]) < 0) {
                int now = i + 1;
                for (int j = i + 2; j < a.length; j++) {
                    if (a[j].compareTo(a[i]) > 0 && a[j].compareTo(a[now]) < 0) {
                        now = j;
                    }
                }
                T t = a[now];
                a[now] = a[i];
                a[i] = t;
                reverse(a, i + 1, a.length);
                return true;
            }
        }
        return false;
    }

    public static <T> boolean nextPermutation(T[] a, Comparator<? super T> comp) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (comp.compare(a[i], a[i + 1]) < 0) {
                int now = i + 1;
                for (int j = i + 2; j < a.length; j++) {
                    if (comp.compare(a[j], a[i]) > 0 && comp.compare(a[j], a[now]) < 0) {
                        now = j;
                    }
                }
                T t = a[now];
                a[now] = a[i];
                a[i] = t;
                reverse(a, i + 1, a.length);
                return true;
            }
        }
        return false;
    }

    public static int[] merge(int[] a, int[] b) {
        int[] ret = new int[a.length + b.length];
        int size = 0;
        for (int i = 0, j = 0; i < a.length || j < b.length; ) {
            if (i < a.length && (j == b.length || a[i] < b[j])) {
                ret[size] = a[i];
                i++;
            } else {
                ret[size] = b[j];
                j++;
            }
            size++;
        }
        return ret;
    }

    public static long[] merge(long[] a, long[] b) {
        long[] ret = new long[a.length + b.length];
        int size = 0;
        for (int i = 0, j = 0; i < a.length || j < b.length; ) {
            if (i < a.length && (j == b.length || a[i] < b[j])) {
                ret[size] = a[i];
                i++;
            } else {
                ret[size] = b[j];
                j++;
            }
            size++;
        }
        return ret;
    }

    public static double[] merge(double[] a, double[] b) {
        double[] ret = new double[a.length + b.length];
        int size = 0;
        for (int i = 0, j = 0; i < a.length || j < b.length; ) {
            if (i < a.length && (j == b.length || a[i] < b[j])) {
                ret[size] = a[i];
                i++;
            } else {
                ret[size] = b[j];
                j++;
            }
            size++;
        }
        return ret;
    }

    public static char[] merge(char[] a, char[] b) {
        char[] ret = new char[a.length + b.length];
        int size = 0;
        for (int i = 0, j = 0; i < a.length || j < b.length; ) {
            if (i < a.length && (j == b.length || a[i] < b[j])) {
                ret[size] = a[i];
                i++;
            } else {
                ret[size] = b[j];
                j++;
            }
            size++;
        }
        return ret;
    }

    public static <T extends Comparable<? super T>> T[] merge(T[] a, T[] b) {
        T[] ret = getNewArray(a, a.length + b.length);
        int size = 0;
        for (int i = 0, j = 0; i < a.length || j < b.length; ) {
            if (i < a.length && (j == b.length || a[i].compareTo(b[j]) <= 0)) {
                ret[size] = a[i];
                i++;
            } else {
                ret[size] = b[j];
                j++;
            }
            size++;
        }
        return ret;
    }

    public static <T> T[] merge(T[] a, T[] b, Comparator<? super T> comp) {
        T[] ret = getNewArray(a, a.length + b.length);
        int size = 0;
        for (int i = 0, j = 0; i < a.length || j < b.length; ) {
            if (i < a.length && (j == b.length || comp.compare(a[i], b[j]) <= 0)) {
                ret[size] = a[i];
                i++;
            } else {
                ret[size] = b[j];
                j++;
            }
            size++;
        }
        return ret;
    }

    public static int[] compress(int[] a) {
        int[] b = a.clone();
        Arrays.sort(b);
        b = unique(b);
        int[] ret = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            int l = -1;
            int r = b.length;
            while (r - l > 1) {
                int m = (l + r) / 2;
                if (b[m] <= a[i]) {
                    l = m;
                } else {
                    r = m;
                }
            }
            ret[i] = l;
        }
        return ret;
    }

    public static int[] compress(long[] a) {
        long[] b = a.clone();
        Arrays.sort(b);
        b = unique(b);
        int[] ret = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            int l = -1;
            int r = b.length;
            while (r - l > 1) {
                int m = (l + r) / 2;
                if (b[m] <= a[i]) {
                    l = m;
                } else {
                    r = m;
                }
            }
            ret[i] = l;
        }
        return ret;
    }
}