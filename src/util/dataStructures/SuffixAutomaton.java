package util.dataStructures;

public class SuffixAutomaton {

    public final int MAX_VERTICES_COUNT;
    public final int ALPHABET;
    public final int ROOT;

    public int[][] next;
    public int[] link;
    public int[] length;
    public boolean[] isClone;
    public int size;

    public SuffixAutomaton(int maxVerticesCount, int alphabet) {
        this.MAX_VERTICES_COUNT = maxVerticesCount;
        this.ALPHABET = alphabet;
        next = new int[ALPHABET][MAX_VERTICES_COUNT];
        link = new int[MAX_VERTICES_COUNT];
        length = new int[MAX_VERTICES_COUNT];
        isClone = new boolean[MAX_VERTICES_COUNT];
        size = 0;
        ROOT = getNode(-1, 0);
    }

    public SuffixAutomaton(int[] array, int alphabet) {
        this(array.length * 2 + 1, alphabet);
        int last = ROOT;
        for (int i = 0; i < array.length; i++) {
            last = append(last, array[i]);
        }
    }

    public SuffixAutomaton(char[] array, int shift, int alphabet) {
        this(toIntArray(array, shift), alphabet);
    }

    public int append(int prev, int symbol) {
        final int now = getNode(0, length[prev] + 1);
        while (prev != -1 && next[prev][symbol] == -1) {
            next[prev][symbol] = now;
            prev = link[prev];
        }
        if (prev != -1) {
            int cur = next[prev][symbol];
            if (length[cur] == length[prev] + 1) {
                link[now] = cur;
            } else {
                int clone = getNode(link[cur], length[prev] + 1);
                isClone[clone] = true;
                for (int i = 0; i < ALPHABET; i++) {
                    next[i][clone] = next[i][cur];
                }
                while (prev != -1 && next[symbol][prev] == cur) {
                    next[symbol][prev] = clone;
                    prev = link[prev];
                }
                link[cur] = clone;
                link[now] = clone;
            }
        }
        return now;
    }

    public static int[] toIntArray(char[] s, int shift) {
        int[] ret = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ret[i] = s[i] + shift;
        }
        return ret;
    }

    private int getNode(int link, int length) {
        for (int i = 0; i < ALPHABET; i++) {
            next[i][size] = -1;
        }
        this.link[size] = link;
        this.length[size] = length;
        return size++;
    }

    public int size() {
        return size;
    }
}
