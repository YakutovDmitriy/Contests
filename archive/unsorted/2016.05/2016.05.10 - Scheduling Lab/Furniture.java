package code;

import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Furniture {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int cost = in.nextInt();
        int[] d = in.nextArrayInt(n);
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        ArrayUtils.sort(order, (i, j) -> Integer.compare(d[i], d[j]));
        ArrayUtils.reverse(order);
        SortedSet<Integer>[] empties = new SortedSet[500];
        for (int i = 0; i < empties.length; i++) {
            empties[i] = new TreeSet<>();
            for (int j = 0; j < m; ++j) {
                empties[i].add(j);
            }
        }
        int[][] ans = new int[n][m];
        for (int job : order) {
            if (d[job] < m) {
                break;
            }
            int need = m;
            int time = d[job] - 1;
            while (need > 0) {

            }
        }
    }
}
