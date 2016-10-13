package code;

import util.FastReader;
import util.FastWriter;
import util.dataStructures.pairs.PairIntInt;

import java.util.*;

public class RobotInField {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        Expression exp = new Expression(in.nextLine());
        int n = in.nextInt();
        int size = 2 * n + 1;
        int m = in.nextInt();
        int k = in.nextInt();
        boolean[][] isFork = new boolean[size][size];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() + n;
            int y = in.nextInt() + n;
            isFork[x][y] = true;
        }
        char[][] invert = new char[size][size];
        for (int i = 0; i < k; i++) {
            int x = in.nextInt() + n;
            int y = in.nextInt() + n;
            char letter = in.nextToken().charAt(0);
            invert[x][y] = letter;
        }
        List<PairIntInt> ans = new ArrayList<>();
        int x = n;
        int y = n;
        int dir = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        while (x >= 0 && x < size && y >= 0 && y < size) {
            ans.add(new PairIntInt(x - n, y - n));
            if (isFork[x][y]) {
                if (exp.evaluate()) {
                    ++dir;
                } else {
                    dir += 3;
                }
                dir %= dx.length;
            }
            if (Character.isLetter(invert[x][y])) {
                exp.flip(invert[x][y]);
            }
            x += dx[dir];
            y += dy[dir];
        }
        for (PairIntInt point : ans) {
            out.println(point.first + " " + point.second);
        }
    }

    private static class Expression {

        private static final String True = "TRUE";
        private static final String False = "FALSE";
        private List<String> tokens;
        private String[] value;

        public Expression(String s) {
            tokens = new ArrayList<>();
            tokens.add("(");
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case ' ':
                        break;
                    case '(':
                    case ')':
                        tokens.add(String.valueOf(s.charAt(i)));
                        break;
                    default:
                        int j = i;
                        while (j < s.length() && Character.isLetter(s.charAt(j))) {
                            j++;
                        }
                        tokens.add(s.substring(i, j));
                        i = j - 1;
                        break;
                }
            }
            tokens.add(")");
            value = new String[26];
            Arrays.fill(value, False);
        }

        void flip(char var) {
            int x = var - 'A';
            value[x] = value[x].equals(True) ? False : True;
        }

        private int prior(String op) {
            switch (op) {
                case "NOT":
                    return 5;
                case "AND":
                    return 2;
                case "OR":
                    return -1;
                case "(":
                case ")":
                    return -3;
                default:
                    System.err.println("op = " + op);
                    throw new AssertionError();
            }
        }

        boolean evaluate() {
            Deque<String> vars = new ArrayDeque<>();
            Deque<String> ops = new ArrayDeque<>();
            for (String s : tokens) {
                switch (s) {
                    case "(":
                        ops.add(s);
                        break;
                    case ")":
                    case "NOT":
                    case "AND":
                    case "OR":
                        while (!ops.isEmpty() && prior(s) < prior(ops.getLast())) {
                            apply(vars, ops);
                        }
                        if (s.equals(")")) {
                            ops.removeLast();
                        } else {
                            ops.add(s);
                        }
                        break;
                    case True:
                    case False:
                        vars.add(s);
                        break;
                    default:
                        vars.add(value[s.charAt(0) - 'A']);
                        break;
                }
            }
            return vars.getLast().equals(True);
        }

        private void apply(Deque<String> vars, Deque<String> ops) {
            switch (ops.pollLast()) {
                case "NOT":
                    String var = vars.pollLast();
                    vars.addLast(var.equals(True) ? False : True);
                    break;
                case "AND":
                    String var1 = vars.pollLast();
                    String var2 = vars.pollLast();
                    vars.addLast(var1.equals(True) && var2.equals(True) ? True : False);
                    break;
                case "OR":
                    var1 = vars.pollLast();
                    var2 = vars.pollLast();
                    vars.addLast(var1.equals(True) || var2.equals(True) ? True : False);
                    break;
            }
        }
    }
}
