package code;

import util.FastReader;
import util.FastWriter;

import java.math.BigInteger;

public class FlyingPig {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int[] b = in.nextArrayInt(in.nextInt());
        a = new BigInteger[b.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = BigInteger.valueOf(b[i]);
        }
        answer = a[0];
        go(0, a.length);
        out.println(answer);
    }

    private void go(int l, int r) {
        if (r - l == 1) {
            answer = answer.max(a[l]);
            return;
        }
        int m = (l + r) / 2;
        go(l, m);
        go(m, r);

        BigInteger maxPLeft = a[m - 1];
        BigInteger minPLeft = a[m - 1];
        BigInteger cur = BigInteger.ONE;
        for (int i = m - 1; i >= l; i--) {
            cur = cur.multiply(a[i]);
            maxPLeft = maxPLeft.max(cur);
            minPLeft = minPLeft.min(cur);
        }

        BigInteger maxPRight = a[m];
        BigInteger minPRight = a[m];
        cur = BigInteger.ONE;
        for (int i = m; i < r; i++) {
            cur = cur.multiply(a[i]);
            maxPRight = maxPRight.max(cur);
            minPRight = minPRight.min(cur);
        }

        answer = answer.max(maxPLeft.multiply(maxPRight));
        answer = answer.max(minPLeft.multiply(maxPRight));
        answer = answer.max(maxPLeft.multiply(minPRight));
        answer = answer.max(minPLeft.multiply(minPRight));
    }

    BigInteger[] a;
    BigInteger answer;
}
