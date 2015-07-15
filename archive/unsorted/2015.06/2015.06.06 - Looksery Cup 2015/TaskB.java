package code;

import util.FastReader;

import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskB {
    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        char[][] a = new char[n][];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextToken().toCharArray();
        }
        int[] now = new int[n];
        int[] bad = new int[n];
        for (int i = 0; i < n; i++) {
            bad[i] = in.nextInt();
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (true) {
            int j = -1;
            for (int i = 0; i < n; i++) {
                if (bad[i] == now[i]) {
                    j = i;
                    break;
                }
            }
            if (j == -1) {
                break;
            }
            ans.add(j + 1);
            for (int i = 0; i < n; i++) {
                if (a[j][i] == '1') {
                    now[i]++;
                }
            }
        }
        out.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) out.print(' ');
            out.print(ans.get(i));
        }
        out.println();
    }


    public void solve1(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        char[][] a = new char[n][];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextToken().toCharArray();
        }
        boolean[][] m = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = a[j][i] == '1';
            }
        }
        boolean[] res = new boolean[n];
        for (int i = 0; i < n; i++) {
            res[i] = in.nextInt() % 2 == 0;
        }
        for (int i = 0; i < n; i++) {

//            for (int i1 = 0; i1 < n; i1++) {
//                for (int j = 0; j < n; j++) {
//                    out.print((m[i1][j] ? 1 : 0) + " ");
//                }
//                out.println("= " + (res[i1] ? 1 : 0));
//            }
//            out.println();

            int j = i;
            while (j < n && !m[j][i]) j++;
            if (j == n) {
                continue;
            }
            boolean[] tmp = m[j];
            m[j] = m[i];
            m[i] = tmp;
            boolean temp = res[i];
            res[i] = res[j];
            res[j] = temp;
            for (j = i + 1; j < n; j++) {
                if (m[j][i]) {
                    for (int k = 0; k < n; k++) {
                        m[j][k] ^= m[i][k];
                    }
                    res[j] ^= res[i];
                }
            }
        }
//        out.println();

        for (int i = n - 1; i > 0; --i) {
//
//            for (int i1 = 0; i1 < n; i1++) {
//                for (int j = 0; j < n; j++) {
//                    out.print((m[i1][j] ? 1 : 0) + " ");
//                }
//                out.println("= " + (res[i1] ? 1 : 0));
//            }
//            out.println();

            if (!m[i][i]) continue;
            for (int j = 0; j < i; j++) {
                if (m[j][i]) {
                    for (int k = 0; k < n; k++) {
                        m[j][k] ^= m[i][k];
                    }
                    res[j] ^= res[i];
                }
            }
        }

//        for (int i1 = 0; i1 < n; i1++) {
//            for (int j = 0; j < n; j++) {
//                out.print((m[i1][j] ? 1 : 0) + " ");
//            }
//            out.println("= " + (res[i1] ? 1 : 0));
//        }
//        out.println();

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (res[i]) {
                ans.add(i + 1);
            }
        }
        out.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) out.print(' ');
            out.print(ans.get(i));
        }
        out.println();
    }
}
