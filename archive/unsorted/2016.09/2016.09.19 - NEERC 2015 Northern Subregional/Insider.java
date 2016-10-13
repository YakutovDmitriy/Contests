package code;

import util.FastReader;
import util.FastWriter;

import java.util.*;

public class Insider {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        allTriples = new HashSet<>();
        countAsB = new int[n];
        ac2t = new List[n];
        for (int i = 0; i < n; i++) {
            ac2t[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            Triple t = new Triple(in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1, i);
            countAsB[t.b]++;
            allTriples.add(t);
            ac2t[t.a].add(t);
            ac2t[t.c].add(t);
        }
        queueZeroB = new int[n];
        headB = 0;
        tailB = 0;
        for (int i = 0; i < n; i++) {
            if (countAsB[i] == 0) {
                queueZeroB[tailB++] = i;
            }
        }
        answer = new int[2 * n + 10];
        headAns = tailAns = n + 5;
        posInAns = new int[n];
        Arrays.fill(posInAns, -1);
        while (headB < tailB) {
            headB++;
            go(queueZeroB[headB - 1]);
        }
        for (int i = headAns; i < tailAns; i++) {
            out.print(answer[i] + 1 + " ");
        }
        out.println();
    }

    int[] answer;
    int headAns, tailAns;
    List<Triple>[] ac2t;
    int[] posInAns;

    int[] queueZeroB;
    int headB, tailB;
    int[] countAsB;
    Set<Triple> allTriples;

    void go(int v) {
        List<Triple> curTriples = new ArrayList<>();
        for (Triple t : ac2t[v]) {
            if (allTriples.contains(t)) {
                curTriples.add(t);
                allTriples.remove(t);
                countAsB[t.b]--;
                if (countAsB[t.b] == 0) {
                    queueZeroB[tailB++] = t.b;
                }
            }
        }
        if (headB < tailB) {
            headB++;
            go(queueZeroB[headB - 1]);
        }
        int toHead = 0;
        int toTail = 0;
        for (Triple t : curTriples) {
            int b = t.b;
            int c = t.a + t.c - v;
            if (posInAns[b] < posInAns[c]) {
                toHead++;
            } else {
                toTail++;
            }
        }
        if (toHead > toTail) {
            answer[--headAns] = v;
            posInAns[v] = headAns;
        } else {
            posInAns[v] = tailAns;
            answer[tailAns++] = v;
        }
    }

    class Triple {
        int a, b, c;
        int index;

        public Triple(int a, int b, int c, int index) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Triple triple = (Triple) o;

            if (a != triple.a) return false;
            if (b != triple.b) return false;
            if (c != triple.c) return false;
            return index == triple.index;

        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            result = 31 * result + c;
            result = 31 * result + index;
            return result;
        }
    }
}
