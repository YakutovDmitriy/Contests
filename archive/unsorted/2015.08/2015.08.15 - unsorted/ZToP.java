package code;

import util.FastReader;
import util.FastWriter;
import util.StringUtils;

public class ZToP {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        out.printArray(StringUtils.zToPrefix(in.nextArrayInt(in.nextInt())));
    }
}
