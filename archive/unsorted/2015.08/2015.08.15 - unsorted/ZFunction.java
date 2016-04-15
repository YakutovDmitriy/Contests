package code;

import util.FastReader;
import util.FastWriter;
import util.StringUtils;

public class ZFunction {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        out.printArray(StringUtils.getZFunction(in.nextToken()));
    }
}
