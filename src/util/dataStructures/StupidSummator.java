package util.dataStructures;

public class StupidSummator {

    private int[] a;

    public StupidSummator(int n) {
        a = new int[n];
    }

    public StupidSummator(int[] array) {
        a = array.clone();
    }

    public int size() {
        return a.length;
    }

    public long getSum(int from, int to) {
        long ret = 0;
        for (int i = from; i < to; i++) {
            ret += a[i];
        }
        return ret;
    }

    public long getSum(int from) {
        return getSum(from, a.length);
    }

    public int get(int index) {
        return a[index];
    }

    public void add(int pos, int value) {
        a[pos] += value;
    }

    public void add(int from, int to, int value) {
        for (int i = from; i < to; i++) {
            a[i] += value;
        }
    }

    public void set(int pos, int value) {
        a[pos] = value;
    }

    public void set(int from, int to, int value) {
        for (int i = from; i < to; i++) {
            a[i] = value;
        }
    }
}
