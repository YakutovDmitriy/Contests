package util.dataStructures;

import java.util.Arrays;

public class SuffixAutomaton {

    public final int MAX_VERTEXES_COUNT;
    public final int ALPHABET;
    public final int ROOT;

    public int[][] next;
    public int[] link;
    public int[] length;
    public boolean[] isClone;
    public int size;

    public SuffixAutomaton(int maxVertexesCount, int alphabet) {
        this.MAX_VERTEXES_COUNT = maxVertexesCount;
        this.ALPHABET = alphabet;
        next = new int[ALPHABET][MAX_VERTEXES_COUNT];
        link = new int[MAX_VERTEXES_COUNT];
        length = new int[MAX_VERTEXES_COUNT];
        isClone = new boolean[MAX_VERTEXES_COUNT];
        size = 0;
        ROOT = getNode(-1, 0);
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

    private int getNode(int link, int length) {
        for (int i = 0; i < ALPHABET; i++) {
            next[i][size] = -1;
        }
        this.link[size] = link;
        this.length[size] = length;
        return size++;
    }

}
