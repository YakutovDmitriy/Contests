package code;

import util.ArrayUtils;
import util.FastReader;
import util.FastWriter;
import util.dataStructures.Pair;

import java.util.*;

public class TaskB {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Pair<Integer, String>>[] scores = new List[m];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            String name = in.nextToken();
            scores[in.nextInt() - 1].add(new Pair<>(in.nextInt(), name));
        }
        for (int i = 0; i < m; i++) {
            Collections.sort(scores[i], (o1, o2) -> Integer.compare(o1.first, o2.first));
            int k = scores[i].size();
            boolean good = k == 2;
            if (!good) {
                good = !Objects.equals(scores[i].get(k - 3).first, scores[i].get(k - 2).first);
            }
            if (good) {
                out.println(scores[i].get(k - 2).second + " " + scores[i].get(k - 1).second);
            } else {
                out.println("?");
            }
        }
    }


}
