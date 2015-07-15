package code;

import util.FastReader;
import util.StringUtils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Search {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        String text = in.next();
        String word = in.next();
        int[] kmp = StringUtils.KnuthMorrisPratt(word, text, '!');
        List<Integer> poses = new ArrayList<>();
        for (int i = 0; i < kmp.length; i++) {
            if (kmp[i] == word.length()) {
                poses.add(i + 1);
            }
        }
        out.println(poses.size());
        for (int x : poses) {
            out.print(x + " ");
        }
        out.println();
    }
}
