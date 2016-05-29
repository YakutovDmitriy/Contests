package util.dataStructures.strings;

import util.ArrayUtils;

import java.util.Arrays;

public class SuffixArrayLinear {

    public final int n;
    public final int alphabet;
    public int[] a;
    public int[] sa;
    public int[] where;

    public int size() {
        return n;
    }

    public SuffixArrayLinear(int[] a, int alphabet) {
        n = a.length;
        this.alphabet = alphabet;
        this.a = new int[n + 3];
        System.arraycopy(a, 0, this.a, 0, a.length);
        sa = new int[n + 3];
        build(this.a, sa, n, alphabet);
        where = new int[n];
        for (int i = 0; i < n; i++) {
            where[sa[i]] = i;
        }
    }

    private static boolean comp(int a1, int a2, int b1, int b2) {
        return a1 < b1 || (a1 == b1 && a2 <= b2);
    }

    private static boolean comp(int a1, int a2, int a3, int b1, int b2, int b3) {
        return a1 < b1 || (a1 == b1 && comp(a2, a3, b2, b3));
    }

    private static void radixSort(int[] a, int[] ord, int[] r, int n, int A) {
        int[] c = new int[A + 1];
        for (int i = 0; i < n; ++i) ++c[1 + r[a[i]]];
        for (int i = 1; i <= A; ++i) c[i] += c[i - 1];
        for (int i = 0; i < n; ++i) ord[c[r[a[i]]]++] = a[i];
    }

    private void build(int[] s, int[] sa, int n, int A) {
        int n0 = (n + 2) / 3, n1 = (n + 1) / 3, n2 = n / 3, n02 = n0 + n2;
        int[] s12 = new int[n02 + 3];
        int[] sa12 = new int[n02 + 3];
        int[] s0 = new int[n0];
        int[] sa0 = new int[n0];
        for (int i = 1, j = 0; i < n + n0 - n1; i += i % 3) {
            s12[j++] = i;
        }

        radixSort(s12, sa12, Arrays.copyOfRange(s, 2, s.length), n02, A);
        radixSort(sa12, s12, Arrays.copyOfRange(s, 1, s.length), n02, A);
        radixSort(s12, sa12, s, n02, A);

        int newA = 0, c0 = -1, c1 = -1, c2 = -1;
        for (int i = 0; i < n02; ++i) {
            if (s[sa12[i]] != c0 || s[sa12[i] + 1] != c1 || s[sa12[i] + 2] != c2) {
                ++newA;
                c0 = s[sa12[i]];
                c1 = s[sa12[i] + 1];
                c2 = s[sa12[i] + 2];
            }
            if (sa12[i] % 3 == 1) {
                s12[sa12[i] / 3] = newA;
            } else {
                s12[sa12[i] / 3 + n0] = newA;
            }
        }

        if (newA < n02) {
            build(s12, sa12, n02, newA);
            for (int i = 0; i < n02; ++i) s12[sa12[i]] = i + 1;
        } else {
            for (int i = 0; i < n02; ++i) sa12[s12[i] - 1] = i;
        }

        for (int i = 0, j = 0; i < n02; ++i) {
            if (sa12[i] < n0) {
                s0[j++] = 3 * sa12[i];
            }
        }
        radixSort(s0, sa0, s, n0, A);

        for (int p = 0, t = n0 - n1, k = 0; k < n; ++k) {
            int i = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
            int j = sa0[p];
            if (sa12[t] < n0 ?
                    comp(s[i], s12[sa12[t] + n0], s[j], s12[j / 3]) :
                    comp(s[i], s[i +1], s12[sa12[t] - n0 + 1], s[j], s[j + 1], s12[j / 3 + n0])) {
                sa[k] = i;
                ++t;
                if (t == n02) {
                    for (++k; p < n0; ++p, ++k) {
                        sa[k] = sa0[p];
                    }
                }
            } else {
                sa[k] = j;
                ++p;
                if (p == n0) {
                    for (++k; t < n02; ++t, ++k) {
                        sa[k] = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
                    }
                }
            }
        }
    }

}
