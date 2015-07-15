package code;

import util.FastReader;
import util.FastWriter;

import java.util.HashSet;

public class TaskA {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        String s = in.nextToken();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i <= s.length(); i++) {
            for (char x = 'a'; x <= 'z'; x++) {
                String now = s.substring(0, i) + x + s.substring(i);
                set.add(now);
            }
        }
        out.println(set.size());
    }
}
