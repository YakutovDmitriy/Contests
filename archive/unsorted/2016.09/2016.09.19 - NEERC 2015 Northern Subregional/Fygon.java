package code;

import util.FastReader;
import util.FastWriter;
import util.math.MathUtils;

import java.util.Arrays;

public class Fygon {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        Program[] stack = new Program[123];
        Program program = null;
        int head = 0;
        for (String line; (line = in.nextLine()) != null; ) {
            int level = 0;
            while (line.charAt(level * 4) == ' ') {
                level++;
            }
            while (head > level) {
                stack[head] = null;
                head--;
            }
            line = line.substring(level * 4);
            if (stack[head] == null) {
                stack[head] = parseProgram(line);
                if (head > 0 && stack[head - 1] instanceof For) {
                    ((For) stack[head - 1]).innerProgram = stack[head];
                }
            } else {
                stack[head].nextLine = parseProgram(line);
                stack[head] = stack[head].nextLine;
            }
            if (program == null) {
                program = stack[head];
            }
            head++;
        }
        int points = 7;
        int[] x = new int[points];
        int[] y = new int[points];
        for (int i = 0; i < points; i++) {
            Arrays.fill(value, 0);
            value['n' - 'a'] = i;
            lagsCount = 0;
            program.execute();
            x[i] = i;
            y[i] = lagsCount;
        }
        Ratio[] ans = interpolate(x, y);
        boolean was = false;
        for (int i = 0; i < ans.length; i++) {
            Ratio r = ans[i];
            if (r.num == 0) {
                continue;
            }
            if (was && r.num > 0) {
                out.print("+");
            }
            out.print(r.num);
            if (r.den > 1) {
                out.print("/" + r.den);
            }
            for (int j = 0; j < i; j++) {
                out.print("*n");
            }
            was = true;
        }
        out.println();
    }

    private static class Ratio {
        final long num, den;

        public Ratio(long num, long den) {
            if (den < 0) {
                num *= -1;
                den *= -1;
            }
            long gcd = MathUtils.gcd(num, den);
            this.num = num / gcd;
            this.den = den / gcd;
        }

        Ratio add(Ratio r) {
            return new Ratio(num * r.den + den * r.num, den * r.den);
        }

        Ratio mul(Ratio r) {
            return new Ratio(num * r.num, den * r.den);
        }

        Ratio invert() {
            return new Ratio(den, num);
        }

        final static Ratio ZERO = new Ratio(0, 1);
        final static Ratio ONE = new Ratio(1, 1);

        public static Ratio valueOf(long number) {
            return new Ratio(number, 1);
        }
    }

    Ratio[] interpolate(int[] x, int[] y) {
        Ratio[] ret = {Ratio.ZERO};
        for (int i = 0; i < x.length; i++) {
            Ratio[] cur = {Ratio.ONE};
            for (int j = 0; j < x.length; j++) {
                if (i != j) {
                    cur = prod(cur, new Ratio[]{Ratio.valueOf(-x[j]), Ratio.ONE});
                }
            }
            Ratio eval = Ratio.ZERO;
            Ratio xRatio = Ratio.valueOf(x[i]);
            for (int j = cur.length - 1; j >= 0; j--) {
                eval = eval.mul(xRatio);
                eval = eval.add(cur[j]);
            }
            Ratio coef = Ratio.valueOf(y[i]).mul(eval.invert());
            for (int j = 0; j < cur.length; j++) {
                cur[j] = cur[j].mul(coef);
            }
            ret = sum(ret, cur);
        }
        return ret;
    }

    Ratio[] sum(Ratio[] a, Ratio[] b) {
        Ratio[] c = new Ratio[Math.max(a.length, b.length)];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            if (c[i] == null) {
                c[i] = b[i];
            } else {
                c[i] = c[i].add(b[i]);
            }
        }
        return c;
    }

    Ratio[] prod(Ratio[] a, Ratio[] b) {
        Ratio[] c = new Ratio[a.length + b.length - 1];
        Arrays.fill(c, Ratio.ZERO);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                c[i + j] = c[i + j].add(a[i].mul(b[j]));
            }
        }
        return c;
    }

    private Program parseProgram(String line) {
        if (line.charAt(0) == 'l') {
            return new Lag();
        }
        int var = line.charAt(4) - 'a';
        int i = line.indexOf('(') + 1;
        int count;
        if (Character.isLetter(line.charAt(i))) {
            count = -(line.charAt(i) - 'a');
        } else {
            count = Integer.parseInt(line.substring(i, line.indexOf(')')));
        }
        return new For(var, count);
    }

    int[] value = new int[26];
    int lagsCount;

    abstract class Program {
        Program nextLine;

        void execute() {
            localAction();
            if (nextLine != null) {
                nextLine.execute();
            }
        }

        abstract void localAction();
    }

    class For extends Program {

        Program innerProgram;
        int count;
        int var;

        For(int var, int count) {
            this.var = var;
            this.count = count;
        }

        @Override
        void localAction() {
            int cnt = count > 0 ? count : value[-count];
            for (value[var] = 0; value[var] < cnt; value[var]++) {
                innerProgram.execute();
            }
        }
    }

    class Lag extends Program {

        @Override
        void localAction() {
            lagsCount++;
        }
    }
}
