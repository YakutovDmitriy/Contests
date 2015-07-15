package code;

import util.FastReader;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Unequal {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        String s = in.nextToken();
        Set<String> substrings = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                substrings.add(s.substring(i, j));
            }
        }
        out.println(substrings.size());
    }
}
