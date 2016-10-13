package code;

import util.FastReader;
import util.FastWriter;

import java.util.ArrayList;
import java.util.List;

public class Easy {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        String s = in.nextToken();
        if (!isSign(s.charAt(0))) {
            s = '+' + s;
        }
        List<String> numbers = new ArrayList<>();
        int start = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (i == s.length() || isSign(s.charAt(i))) {
                numbers.add(s.substring(start, i));
                start = i;
            }
        }
        for (String number : numbers) {
            out.print(modify(number));
        }
        out.println();
    }

    private String modify(String s) {
        if (s.length() == 2 || s.charAt(0) == '+') {
            return s;
        }
        int i = 2;
        while (i < s.length() - 1 && s.charAt(i) == '0') {
            i++;
        }
        return modify(s.substring(0, i)) + '+' + s.substring(i);
    }

    boolean isSign(char x) {
        return x == '-' || x == '+';
    }
}
