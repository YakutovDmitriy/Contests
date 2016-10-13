package util;

import util.dataStructures.strings.SuffixAutomaton;

import java.util.Arrays;

public class StringUtils {

    private StringUtils() {}

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
        return Arrays.copyOfRange(z, a.length + 1, z.length);
    }

    public static int[] getManacher(int[] a) {
        int n = a.length;
        int[] ret = new int[n];
        int l = -1;
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (i < r) {
                ret[i] = Math.min(ret[l + r - i], r - i);
            }
            while (i - ret[i] >= 0 && i + ret[i] < n && a[i - ret[i]] == a[i + ret[i]]) {
                ret[i]++;
            }
            if (i + ret[i] > r) {
                l = i - ret[i];
                r = i + ret[i];
            }
        }
        return ret;
    }

    public static int[] getManacher(char[] a) {
        int n = a.length;
        int[] ret = new int[n];
        int l = -1;
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (i < r) {
                ret[i] = Math.min(ret[l + r - i], r - i);
            }
            while (i - ret[i] >= 0 && i + ret[i] < n && a[i - ret[i]] == a[i + ret[i]]) {
                ret[i]++;
            }
            if (i + ret[i] > r) {
                l = i - ret[i];
                r = i + ret[i];
            }
        }
        return ret;
    }

    public static int[] getManacher(String a) {
        int n = a.length();
        int[] ret = new int[n];
        int l = -1;
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (i < r) {
                ret[i] = Math.min(ret[l + r - i], r - i);
            }
            while (i - ret[i] >= 0 && i + ret[i] < n && a.charAt(i - ret[i]) == a.charAt(i + ret[i])) {
                ret[i]++;
            }
            if (i + ret[i] > r) {
                l = i - ret[i];
                r = i + ret[i];
            }
        }
        return ret;
    }

    public static long getSubstringCount(SuffixAutomaton dawg) {
        long result = 0;
        for (int i = 0; i < dawg.size; i++) {
            if (i != dawg.ROOT) {
                result += dawg.length[i] - dawg.length[dawg.link[i]];
            }
        }
        return result;
    }

    public static int[] toIntArray(char[] s, int shift) {
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = s[i] + shift;
        }
        return a;
    }

    public static int[] toIntArray(String s, int shift) {
        int[] a = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i) + shift;
        }
        return a;
    }
}