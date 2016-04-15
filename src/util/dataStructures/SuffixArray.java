package util.dataStructures;

import util.dataStructures.segments.SparseTableMin;

import java.util.Arrays;

public class SuffixArray {

    public int n;
    public int alphabet;
    public int[] a;
    public int[] sa;
    public int[] cl;
    public int[] where;

    public int size() {
        return n;
    }

    public SuffixArray(int[] a) {
        n = a.length;
        this.a = a.clone();
        sa = new int[n];
        alphabet = 0;
        for (int x : a) {
            alphabet = Math.max(alphabet, x);
        }
        alphabet += 2;
        cl = new int[Math.max(n, alphabet)];
        where = new int[n];
        build();
    }

    public static int[] toIntArray(char[] s, int shift) {
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = s[i] + shift;
        }
        return a;
    }

    public int[] lcp;
    public SparseTableMin table;

    public void buildLCP() {
        lcp = new int[n - 1];
        int last = 0;
        for (int i = 0; i < n; i++) {
            if (last > 0) {
                last--;
            }
            if (where[i] == n - 1) {
                last = 0;
                continue;
            }
            int j = sa[where[i] + 1];
            while (i + last < n && j + last < n && a[i + last] == a[j + last]) {
                last++;
            }
            lcp[where[i]] = last;
        }
        table = null;
    }

    public int getLCP(int ithPosInSA, int jthPosInSA) {
        if (ithPosInSA == jthPosInSA) {
            return n - sa[ithPosInSA];
        }
        if (lcp == null) {
            buildLCP();
        }
        if (ithPosInSA > jthPosInSA) {
            int temp = ithPosInSA; ithPosInSA = jthPosInSA; jthPosInSA = temp;
        }
        if (ithPosInSA + 1 == jthPosInSA) {
            return lcp[ithPosInSA];
        }
        if (table == null) {
            table = new SparseTableMin(lcp);
        }
        return table.getMin(ithPosInSA, jthPosInSA);
    }

    private void build() {
        System.arraycopy(a, 0, cl, 0, a.length);
        int[] start = new int[cl.length];
        for (int i = 0; i < n; i++) {
            start[cl[i] + 1]++;
        }
        for (int i = 1; i < alphabet; i++) {
            start[i] += start[i - 1];
        }
        for (int i = 0; i < n; i++) {
            sa[start[cl[i]]++] = i;
        }
        Arrays.fill(start, 0);
        for (int i = 0; i < n; i++) {
            start[cl[i] + 1]++;
        }
        for (int i = 1; i < alphabet; i++) {
            start[i] += start[i - 1];
        }
        int[] newSa = new int[n];
        int[] newCl = new int[cl.length];
        for (int half = 1; half < n; half *= 2) {
            for (int i = 0; i < n; i++) {
                sa[i] -= half;
                if (sa[i] < 0) sa[i] += n;
            }
            for (int i = 0; i < n; i++) {
                int x = sa[i];
                newSa[start[cl[x]]++] = x;
            }
            newCl[newSa[0]] = 0;
            int last = 0;
            start[0] = 0;
            for (int i = 0; i + 1 < n; i++) {
                int x = newSa[i];
                int y = newSa[i + 1];
                int xx = x + half;
                if (xx >= n) xx -= n;
                int yy = y + half;
                if (yy >= n) yy -= n;
                if (cl[x] != cl[y] || cl[xx] != cl[yy]) {
                    newCl[y] = ++last;
                    start[last] = i + 1;
                } else {
                    newCl[y] = newCl[x];
                }
            }
            int[] temp = cl; cl = newCl; newCl = temp;
            temp = sa; sa = newSa; newSa = temp;
        }
        for (int i = 0; i < n; i++) {
            where[sa[i]] = i;
        }
    }
}
