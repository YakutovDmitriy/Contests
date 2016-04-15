package code;

import util.FastReader;
import util.FastWriter;
import util.math.MathUtils;

public class FreeCell {

    static final String possible = "Possible";
    static final String broken = "Broken";

    public void solve(int testNumber, FastReader in, FastWriter out) {
        out.print("Case #" + testNumber + ": ");
        long n = in.nextLong();
        int pd = in.nextInt();
        int pg = in.nextInt();
        if (pg == 100) {
            out.println(pd == 100 ? possible : broken);
        } else if (pg == 0) {
            out.println(pd == 0 ? possible : broken);
        } else {
            try {
                int need = 100 / MathUtils.gcd(pd, 100);
                out.println(need <= n ? possible : broken);
            } catch (ArithmeticException e) {
                System.err.println(pd + " " + 100 + " " + MathUtils.gcd(pd, 100));
                throw e;
            }
        }
    }
}
