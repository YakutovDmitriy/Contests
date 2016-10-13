package code;

import util.FastReader;
import util.FastWriter;

import java.math.BigInteger;

public class FlyingPigVer2 {

    int[] a;
    final int C = 1000;
    BigInteger[] bia;
    BigInteger[] blocks;

    BigInteger prod(int i, int j) {
        if (i >= j) {
            return BigInteger.ZERO;
        }
        BigInteger ret = BigInteger.ONE;
        while (i < j && i % C != 0) {
            ret = ret.multiply(bia[i]);
            i++;
        }
        while (i + C < j) {
            ret = ret.multiply(blocks[i / C]);
            i += C;
        }
        while (i < j) {
            ret = ret.multiply(bia[i]);
            i++;
        }
        return ret;
    }

    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        a = in.nextArrayInt(n);
        if (n == 1) {
            out.println(a[0]);
            return;
        }
        bia = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            bia[i] = BigInteger.valueOf(a[i]);
        }
        blocks = new BigInteger[n / C];
        for (int i = 0; i < blocks.length; i++) {
            BigInteger prod = BigInteger.ONE;
            for (int j = 0; j < C; j++) {
                prod = prod.multiply(bia[i * C + j]);
            }
            blocks[i] = prod;
        }
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                continue;
            }
            int j = i;
            int negs = 0;
            int neg1 = -1;
            int neg2 = -1;
            while (j < n && a[j] != 0) {
                if (a[j] < 0) {
                    if (neg1 < 0) {
                        neg1 = j;
                    }
                    neg2 = j;
                    ++negs;
                }
                ++j;
            }
            if (negs % 2 == 0) {
                ans = ans.max(prod(i, j));
            } else {
                ans = ans.max(prod(i, neg2));
                ans = ans.max(prod(neg1 + 1, j));
            }
            i = j;
        }
        out.println(ans);
    }
}
