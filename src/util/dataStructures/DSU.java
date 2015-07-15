package util.dataStructures;

import java.util.Arrays;

public class DSU {

    private int[] pr;

    public DSU(int n) {
        pr = new int[n];
        clear();
    }

    public int size() {
        return pr.length;
    }

    public int getSizeOf(int v) {
        return -pr[getRootOf(v)];
    }

    public void clear() {
        Arrays.fill(pr, -1);
    }

    public int getRootOf(int v) {
        int ret = v;
        while (pr[ret] >= 0) {
            ret = pr[ret];
        }
        while (pr[v] >= 0) {
            int to = pr[v];
            pr[v] = ret;
            v = to;
        }
        return ret;
    }

    public boolean unite(int a, int b) {
        a = getRootOf(a);
        b = getRootOf(b);
        if (a == b) {
            return false;
        }
        if (-pr[a] < -pr[b]) {
            pr[b] += pr[a];
            pr[a] = b;
        } else {
            pr[a] += pr[b];
            pr[b] = a;
        }
        return true;
    }
}
