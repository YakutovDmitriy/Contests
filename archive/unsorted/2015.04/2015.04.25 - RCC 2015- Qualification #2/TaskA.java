package code;

import util.FastReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        if (k == 1 && a >= 99 && b >= 99) {
            out.println(0);
            return;
        }
        int x = Math.max(0, Math.min(a - 99, b - 99));
        a -= x;
        b -= x;
        while (a > 0 && b > 0) {
            int va = Math.min(a, 99);
            int vb = Math.min(b, 99);
            if (va * k == vb || vb * k == va) {
                out.println(x);
                return;
            }
            x++;
            a--;
            b--;
        }
        out.println(-1);
    }
}
