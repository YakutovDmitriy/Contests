package util.math;

import util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class MathUtils {

    public static double sqrt(double x) {
        double ret = x;
        for (int i = 0; i < 15; i++) {
            ret = (ret + x / ret) / 2;
        }
        return ret;
    }

    public static int modPow(int base, long indicator, int module) {
        int ret = 1;
        for (; indicator > 0; indicator >>= 1) {
            if ((indicator & 1) == 1) {
                ret = (int) ((long) ret * base % module);
            }
            base = (int) ((long) base * base % module);
        }
        return ret;
    }

    public static int[][] getC(int n, int mod) {
        int[][] ret = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            ret[i] = new int[i + 1];
            ret[i][0] = 1;
            if (ret[i][0] >= mod) ret[i][0] -= mod;
            for (int j = 1; j <= n; j++) {
                ret[i][j] = ret[i - 1][j - 1] + ret[i - 1][j];
                if (ret[i][j] >= mod) ret[i][j] -= mod;
            }
        }
        return ret;
    }

    public static int[] getFactorials(int n, int mod) {
        int[] fact = new int[n + 1];
        fact[0] = 1 % mod;
        for (int i = 1; i <= n; i++) {
            fact[i] = (int) ((long) fact[i - 1] * i % mod);
        }
        return fact;
    }

    public static int getInversed(int number, int modulo) {
        int res = (int) (extendedEuclidean(number, modulo)[0] % modulo);
        if ((long) res * number % modulo != 1) {
            return -1;
        }
        return res;
    }

    public static int[] getInversedFactirials(int n, int mod) {
        int[] ret = new int[n + 1];
        ret[0] = 1;
        for (int i = 1; i <= n; i++) {
            ret[i] = (int) ((long) ret[i - 1] * getInversed(i, mod));
        }
        return ret;
    }

    public static int[] getCatalans(int n, int mod) {
        int[] fact = getFactorials(2 * n, mod);
        int[] rev = getInversedFactirials(n + 1, mod);
        int[] c = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int now = fact[2 * i];
            now = (int) ((long) now * rev[i] % mod);
            now = (int) ((long) now * rev[i + 1] % mod);
            c[i] = now;
        }
        return c;
    }

    public static long[] extendedEuclidean(long a, long b) {
        if (b == 0) {
            return new long[] {1, 0};
        }
        long[] res = extendedEuclidean(b, a % b);
        // b * x + (a - a / b * b) * y == GCD
        // b * (1 - a / b) * x + a * y == GCD
        long temp = res[0] * (1 - a / b);
        res[0] = res[1];
        res[1] = temp;
        return res;
    }

    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b > 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b > 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static long getPhi(long n) {
        long ret = n;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                ret -= ret / i;
                do {
                    n /= i;
                } while (n % i == 0);
            }
        }
        if (n > 1) {
            ret -= ret / n;
        }
        return ret;
    }

    public static int getPhi(int n) {
        int ret = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                ret -= ret / i;
                do {
                    n /= i;
                } while (n % i == 0);
            }
        }
        if (n > 1) {
            ret -= ret / n;
        }
        return ret;
    }

    public static boolean[] getSieveTo(int n) {
        boolean[] ret = new boolean[n + 1];
        Arrays.fill(ret, true);
        ret[0] = false;
        ret[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (ret[i]) {
                for (int j = i * i; j <= n; j += i) {
                    ret[j] = false;
                }
            }
        }
        return ret;
    }

    public static int[] getPrimesTo(int n) {
        boolean[] sieve = getSieveTo(n);
        int[] ret = new int[n];
        int size = 0;
        for (int i = 2; i <= n; i++) {
            if (sieve[i]) {
                ret[size++] = i;
            }
        }
        return Arrays.copyOf(ret, size);
    }

    public static int[] getPrimeDivisorsOfNumbersTo(int n) {
        int[] d = new int[n + 1];
        Arrays.fill(d, -1);
        for (int i = 2; i * i <= n; i++) {
            if (d[i] == -1) {
                for (int j = i; j <= n; j += i) {
                    d[j] = i;
                }
            }
        }
        return d;
    }

    public static int[] getDivisorsOf(int n) {
        ArrayList<Integer> small = new ArrayList<>();
        ArrayList<Integer> big = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                small.add(i);
                if (i * i < n) {
                    big.add(i);
                }
            }
        }
        int[] ret = new int[small.size() + big.size()];
        for (int i = 0; i < small.size(); i++) {
            ret[i] = small.get(i);
        }
        for (int i = 0; i < big.size(); i++) {
            ret[i + small.size()] = big.get(big.size() - 1 - i);
        }
        return ret;
    }

    public static int[] getPrimeDivisorsOf(int n) {
        ArrayList<Integer> p = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                p.add(i);
                do {
                    n /= i;
                } while (n % i == 0);
            }
        }
        if (n > 1) {
            p.add(n);
        }
        return ArrayUtils.toIntArray(p);
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int getNextPrime(int x) {
        while (!isPrime(x)) {
            x++;
        }
        return x;
    }

    public static int[][] multiply(int[][] a, int[][] b, int mod) {
        if (a == null || b == null || b.length == 0 || a.length == 0 || a[0].length != b.length || mod <= 0) {
            return null;
        }
        int n = a.length;
        int m = b.length;
        int l = b[0].length;
        int[][] res = new int[n][l];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                long now = 0;
                for (int k = 0; k < m; k++) {
                    now += (long) a[i][k] * b[k][j];
                }
                now %= mod;
                res[i][j] = (int) now;
            }
        }
        return res;
    }

    public static int[][] pow(int[][] base, int indicator, int mod) {
        if (indicator < 0 || mod <= 0 || base == null || base[0] == null || base.length != base[0].length) {
            return null;
        }
        if (indicator == 1) {
            return base;
        }
        int n = base.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }
        if (indicator == 0) {
            return res;
        }
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            a[i] = base[i].clone();
        }
        for (; indicator > 0; indicator >>= 1) {
            if ((indicator & 1) == 1) {
                res = multiply(res, a, mod);
            }
            a = multiply(a, a, mod);
        }
        return res;
    }

}