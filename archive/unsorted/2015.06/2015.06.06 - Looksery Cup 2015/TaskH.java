package code;

import util.FastReader;

import java.io.PrintWriter;
import java.util.Locale;

public class TaskH {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        final double EPS = 1e-9;
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();
        long d = in.nextInt();
        if (a * d == b * c) {
            out.println(0);
            return;
        }
        double l = 0;
        double r = 1e9;
        for (int it = 0; it < 150; it++) {
            double eps = (l + r) / 2;
            double minAD = Math.min(
                    Math.min((a + eps) * (d + eps), (a + eps) * (d - eps)),
                    Math.min((a - eps) * (d + eps), (a - eps) * (d - eps))
            );
            double maxAD = Math.max(
                    Math.max((a + eps) * (d + eps), (a + eps) * (d - eps)),
                    Math.max((a - eps) * (d + eps), (a - eps) * (d - eps))
            );
            double minBC = Math.min(
                    Math.min((b + eps) * (c + eps), (b + eps) * (c - eps)),
                    Math.min((b - eps) * (c + eps), (b - eps) * (c - eps))
            );
            double maxBC = Math.max(
                    Math.max((b + eps) * (c + eps), (b + eps) * (c - eps)),
                    Math.max((b - eps) * (c + eps), (b - eps) * (c - eps))
            );
            double min = minAD - maxBC;
            double max = maxAD - minBC;
            if (min < -EPS && max > EPS) {
                r = eps;
            } else {
                l = eps;
            }
        }
        out.printf(new Locale("US"), "%.15f\n", (l + r) / 2);
    }
}
