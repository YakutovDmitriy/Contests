package fcb;

import java.util.Arrays;

public class SparseTableRMQ implements RangeMinimumQuery {

    private int[] array;
    private int[] logs;
    private int[][] t;

    @Override
    public void buildRMQ(int[] array) {
        this.array = array.clone();
        int sz = 1;
        while ((1 << sz) <= array.length) {
            sz++;
        }
        t = new int[sz][array.length];
        Arrays.setAll(t[0], i -> i);
        for (int i = 1; i < sz; i++) {
            int len = 1 << i;
            for (int j = 0; j + len <= array.length; j++) {
                int x = t[i - 1][j];
                int y = t[i - 1][j + (len >> 1)];
                t[i][j] = array[x] <= array[y] ? x : y;
            }
        }
        logs = new int[array.length + 1];
        for (int i = 2; i < logs.length; i++) {
            logs[i] = 1 + logs[i >> 1];
        }
    }

    @Override
    public int getMinPos(int from, int to) {
        int i = logs[to - from];
        int x = t[i][from];
        int y = t[i][to - (1 << i)];
        return array[x] <= array[y] ? x : y;
    }

    @Override
    public String getName() {
        return "sparse rmq";
    }
}
