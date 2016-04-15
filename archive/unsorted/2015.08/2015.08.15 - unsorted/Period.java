package code;

import util.FastReader;
import util.FastWriter;
import util.StringUtils;

public class Period {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        out.println(StringUtils.getPeriod(in.nextCharArray()));
    }
}
