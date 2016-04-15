package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.SuffixArray;
import util.dataStructures.segments.BinaryIndexedTreeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskE {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int cntQ = in.nextInt();
        int[] summary = new int[400_400];
        int[] color = new int[summary.length];
        int[] poses = new int[n];
        int[] length = new int[n];
        int size = 0;
        for (int i = 0; i < n; ++i) {
            int[] s = SuffixArray.toIntArray(in.nextCharArray(), -'a' + 1);
            poses[i] = size;
            length[i] = s.length;
            for (int x : s) {
                color[size] = i;
                summary[size++] = x;
            }
            color[size] = -1;
            summary[size++] = 27 + i;
        }
        summary = Arrays.copyOf(summary, size);
        color = Arrays.copyOf(color, size);
        SuffixArray sa = new SuffixArray(summary);
        Query[] queries = new Query[cntQ];
        for (int i = 0; i < cntQ; i++) {
            queries[i] = new Query(in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1);
        }
        for (Query q : queries) {
            int x = sa.where[poses[q.k]];
            int len = length[q.k];
            int left = x;
            int right = sa.size();
            while (right - left > 1) {
                int y = (left + right) / 2;
                if (sa.getLCP(x, y) >= len) {
                    left = y;
                } else {
                    right = y;
                }
            }
            q.right = left;
            left = -1;
            right = x;
            while (right - left > 1) {
                int y = (left + right) / 2;
                if (sa.getLCP(x, y) >= len) {
                    right = y;
                } else {
                    left = y;
                }
            }
            q.left = right;
        }
        List<Query>[] queriesByValue = new List[n];
        for (int i = 0; i < queriesByValue.length; i++) {
            queriesByValue[i] = new ArrayList<>();
        }
        for (Query q : queries) {
            if (q.l > 0) {
                queriesByValue[q.l - 1].add(q);
            }
            queriesByValue[q.r].add(q);
        }
        List<Integer>[] posesByValue = new List[n];
        for (int i = 0; i < posesByValue.length; i++) {
            posesByValue[i] = new ArrayList<>();
        }
        for (int i = 0; i < color.length; i++) {
            if (color[i] >= 0) {
                posesByValue[color[i]].add(sa.where[i]);
            }
        }
        BinaryIndexedTreeSum tree = new BinaryIndexedTreeSum(sa.size());
        for (int c = 0; c < n; c++) {
            for (Integer pos : posesByValue[c]) {
                tree.add(pos, 1);
            }
            for (Query q : queriesByValue[c]) {
                int now = tree.getSum(q.left, q.right + 1);
                if (q.l - 1 == c) {
                    now *= -1;
                }
                q.answer += now;
            }
        }
        for (Query q : queries) {
            out.println(q.answer);
        }
    }

    class Query {
        int l, r, k;
        int left, right;
        int answer;

        public Query(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }
    }
}
