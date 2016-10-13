package code;

import util.FastReader;
import util.FastWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hash {
    public void solve(int testNumber, FastReader in, FastWriter out) {

        char big = 0, small = 0;
        final int base = 31;

        for (char x = 'b'; x <= 'y'; ++x) {
            for (char y = 'B'; y <= 'Y'; ++y) {
                if (x - y == base) {
                    big = x;
                    small = y;
                }
                if (y - x == base) {
                    big = x;
                    small = y;
                }
            }
        }

        System.err.println(big + " and " + small);

        int len = 30;
        char[] s = new char[len];
        Arrays.fill(s, big);

        List<String> strings = new ArrayList<>();

        for (int mask = 0; mask < 1 << len / 2; mask++) {
            char[] cur = s.clone();
            for (int i = 0; i < len; i += 2) {
                if (((mask >> i / 2) & 1) == 1) {
                    ++cur[i];
                    cur[i + 1] -= base;
                }
            }
            strings.add(String.valueOf(cur));
        }

        for (int i = 0; i < strings.size() - 1; i++) {
            if (getHash(strings.get(i)) != getHash(strings.get(i + 1))) {
                System.err.println("OPS!!!");
            }
        }

        int count = in.nextInt();
        for (int i = 0; i < count; i++) {
            out.println(strings.get(i));
        }
    }

    private int getHash(String s) {
        int ret = 0;
        for (char x : s.toCharArray()) {
            ret = 31 * ret + x;
        }
        return ret;
    }
}
