package code;

import util.FastReader;
import util.StringUtils;

import java.io.PrintWriter;

public class Nenokku_easy {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        String text = "";
        while (in.hasMoreTokens()) {
            switch (in.nextToken()) {
                case "A":
                    text += in.nextToken().toLowerCase();
                    break;
                case "?":
                    String word = in.nextToken().toLowerCase();
                    int[] a = StringUtils.KnuthMorrisPratt(word, text, '@');
                    boolean has = false;
                    for (int x : a) {
                        has |= x == word.length();
                    }
                    out.println(has ? "YES" : "NO");
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
