package code;

public class BinaryCode {
    public String[] decode(String message) {
        String[] ret = new String[2];
        int n = message.length();
        iters: for (int it = 0; it < 2; it++) {
            char[] a = new char[n];
            a[0] = (char) ('0' + it);
            for (int i = 1; i < n; i++) {
                int now = message.charAt(i - 1) - a[i - 1];
                if (i > 1) {
                    now -= a[i - 2] - '0';
                }
                a[i] = (char) (now + '0');
                if (a[i] != '0' && a[i] != '1') {
                    ret[it] = "NONE";
                    continue iters;
                }
            }
            if (n > 1 && a[n - 1] + a[n - 2] - '0' != message.charAt(n - 1)) {
                ret[it] = "NONE";
                continue iters;
            }
            if (n == 1 && it + '0' != message.charAt(0)) {
                ret[it] = "NONE";
                continue iters;
            }
            ret[it] = new String(a);
        }
        return ret;
    }
}
