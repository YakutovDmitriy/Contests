package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.Eertree;

public class Task1960 {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        char[] s = in.nextCharArray();
        int n = s.length;
        Eertree tree = new Eertree(n, 26);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int letter = s[i] - 'a';
            tree.append(letter);
            ans[i] = tree.size() - 2;
        }
        out.printArray(ans);
    }
}
