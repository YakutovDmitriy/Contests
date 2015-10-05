package util;

public abstract class StringUtils {

    public static int getPeriod(char[] s) {
        int[] prefix = getPrefixFunction(s);
        int length = s.length - prefix[s.length - 1];
        if (s.length % length == 0) {
            return length;
        } else {
            return s.length;
        }
    }

    public static int getPeriod(int[] s) {
        int[] prefix = getPrefixFunction(s);
        int length = s.length - prefix[s.length - 1];
        if (s.length % length == 0) {
            return s.length / length;
        } else {
            return 1;
        }
    }

    public static int getPeriod(String s) {
        int[] prefix = getPrefixFunction(s);
        int length = s.length() - prefix[s.length() - 1];
        if (s.length() % length == 0) {
            return s.length() / length;
        } else {
            return 1;
        }
    }

    public static int[] getZFunction(final char[] s) {
        int n = s.length;
        int[] z = new int[n];
        int l = 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            if (i < r) {
                z[i] = Math.min(z[i - l], r - i);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                z[i]++;
            }
            if (i + z[i] > r) {
                l = i;
                r = i + z[i];
            }
        }
        return z;
    }

    public static int[] getZFunction(final int[] s) {
        int n = s.length;
        int[] z = new int[n];
        int l = 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            if (i < r) {
                z[i] = Math.min(z[i - l], r - i);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                z[i]++;
            }
            if (i + z[i] > r) {
                l = i;
                r = i + z[i];
            }
        }
        return z;
    }

    public static int[] getZFunction(final String s) {
        int n = s.length();
        int[] z = new int[n];
        int l = 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            if (i < r) {
                z[i] = Math.min(z[i - l], r - i);
            }
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            if (i + z[i] > r) {
                l = i;
                r = i + z[i];
            }
        }
        return z;
    }

    public static int[] getPrefixFunction(final char[] s) {
        int n = s.length;
        int[] p = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && s[i] != s[j]) {
                j = p[j - 1];
            }
            if (s[i] == s[j]) {
                j++;
            }
            p[i] = j;
        }
        return p;
    }

    public static int[] getPrefixFunction(final int[] s) {
        int n = s.length;
        int[] p = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && s[i] != s[j]) {
                j = p[j - 1];
            }
            if (s[i] == s[j]) {
                j++;
            }
            p[i] = j;
        }
        return p;
    }

    public static int[] getPrefixFunction(final String s) {
        int n = s.length();
        int[] p = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = p[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            p[i] = j;
        }
        return p;
    }

    public static int[] zToArray(int[] z) {
        int[] s = new int[z.length];
        int last = 0;
        int pos = 1;
        for (int i = 1; i < z.length; ++i) {
            for (int j = Math.max(i, pos); j < i + z[i]; ++j) {
                s[j] = s[j - i];
                pos = j + 1;
            }
            if (i + z[i] < z.length && s[z[i]] == s[i + z[i]]) {
                s[i + z[i]] = ++last;
            }
        }
        return s;
    }

    public static int[] zToPrefix(int[] z) {
        return getPrefixFunction(zToArray(z));
    }

    public static int[] prefixToZ(int[] prefix) {
        int[] z = new int[prefix.length];
        for (int i = 1; i < prefix.length; ++i) {
            int j = i - prefix[i] + 1;
            z[j] = Math.max(z[j], prefix[i]);
        }
        for (int l = 0, i = 1; i < prefix.length; ++i) {
            if (i + z[i] > l + z[l]) {
                l = i;
            }
            z[i] = Math.max(z[i], Math.min(z[i - l], l + z[l] - i));
        }
        return z;
    }

    public static int[] KnuthMorrisPratt(final String a, final String b, final char separator) {
        int[] z = getZFunction(a + separator + b);
        int[] ret = new int[b.length()];
        System.arraycopy(z, a.length() + 1, ret, 0, b.length());
        return ret;
    }

    public static int[] KnuthMorrisPratt(char[] a, char[] b, final char separator) {
        char[] temp = new char[a.length + 1 + b.length];
        System.arraycopy(a, 0, temp, 0, a.length);
        temp[a.length] = separator;
        System.arraycopy(b, 0, temp, a.length + 1, b.length);
        int[] z = getZFunction(temp);
        int[] ret = new int[b.length];
        System.arraycopy(z, a.length + 1, ret, 0, b.length);
        return ret;
    }

    public static int[] KnuthMorrisPratt(int[] a, int[] b, final int separator) {
        int[] temp = new int[a.length + 1 + b.length];
        System.arraycopy(a, 0, temp, 0, a.length);
        temp[a.length] = separator;
        System.arraycopy(b, 0, temp, a.length + 1, b.length);
        int[] z = getZFunction(temp);
        int[] ret = new int[b.length];
        System.arraycopy(z, a.length + 1, ret, 0, b.length);
        return ret;
    }

    public static int[] getOddPalindromes(char[] s) {
        int l = 0;
        int r = -1;
        int[] ret = new int[s.length];
        for (int i = 0; i < s.length; ++i) {
            int j = 1;
            if (i <= r) {
                j = Math.min(ret[l + r - i] + 1, r - i + 1);
            }
            while (i + j < s.length && i - j >= 0 && s[i + j] == s[i - j]) {
                ++j;
            }
            ret[i] = j;
            --j;
            if (i + j > r) {
                l = i - j;
                r = i + j;
            }
        }
        return ret;
    }

    public static int[] getEvenPalindromes(char[] s) {
        int l = 0;
        int r = -1;
        int[] ret = new int[s.length];
        for (int i = 0; i < s.length; ++i) {
            int j = 1;
            if (i <= r) {
                j = Math.min(ret[l + r - i + 1] + 1, r - i + 2);
            }
            while (i + j - 1 < s.length && i - j >= 0 && s[i + j - 1] == s[i - j]) {
                ++j;
            }
            --j;
            ret[i] = j;
            if (i + j - 1 > r) {
                l = i - j;
                r = i + j - 1;
            }
        }
        return ret;
    }

    public static long getPalindromesCount(char[] s) {
        long ret = 0;
        for (int x : getEvenPalindromes(s)) {
            ret += x;
        }
        for (int x : getOddPalindromes(s)) {
            ret += x;
        }
        return ret;
    }
}