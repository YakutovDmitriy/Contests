package code;

import util.FastReader;
import util.FastWriter;

import java.util.HashMap;
import java.util.HashSet;

public class TaskB {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = in.nextToken();
            Integer x = map.get(s);
            if (x == null) x = 0;
            x++;
            map.put(s, x);
        }
        int ans = 0;
        for (Integer x : map.values()) {
            ans = Math.max(ans, x);
        }
        out.println(ans);
    }
}
