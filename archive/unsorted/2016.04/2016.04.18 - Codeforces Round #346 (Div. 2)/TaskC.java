package code;

import util.FastReader;
import util.FastWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskC {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Set<Integer> have = new HashSet<>();
        for (int i = 0; i < n; i++) {
            have.add(in.nextInt());
        }
        List<Integer> ans = new ArrayList<>();
        for (int x = 1; x <= m; x++) {
            if (!have.contains(x)) {
                ans.add(x);
                m -= x;
            }
        }
        out.println(ans.size());
        out.printCollection(ans);
    }
}
