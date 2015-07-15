package code;

import util.FastReader;

import java.io.PrintWriter;

public class TaskC {
    final String S = "Stannis";
    final String D = "Daenerys";

    public void solve(int testNumber, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int count = n - in.nextInt();
        int a = 0;
        int b = 0;
        for (int i = 0; i < n; i++) {
            if (in.nextInt() % 2 == 1) {
                b++;
            } else {
                a++;
            }
        }
        if (count == 0) {
            out.println(b % 2 == 0 ? D : S);
            return;
        }
        if (count % 2 == 1) {
            int first = Math.max(0, count / 2 - a);
            int second = Math.min(b, count / 2);
            if (first + second >= b) {
                /// ������� ��� ��������
                out.println(D);
            } else {
                first = Math.max(0, count / 2 - b);
                second = Math.min(a, count / 2);
                if (first + second >= a) {
                    /// ������� ��� ������
                    int rest = n - count;
                    if (rest % 2 == 1) {
                        out.println(S);
                    } else {
                        out.println(D);
                    }
                } else {
                    /// ���� � ������, � ��������
                    out.println(S);
                }
            }
        } else {
            int first = Math.max(0, count / 2 - a);
            int second = Math.min(b, count / 2);
            if (first + second >= b) {
                /// ������� ��� ��������
                out.println(D);
            } else {
                first = Math.min(a, count / 2);
                second = Math.max(0, count / 2 - 1 - b);
                if (first + second < a) {
                    /// ���� � ��, � ��
                    out.println(D);
                } else {
                    /// �������� ��������
                    int rest = n - count;
                    if (rest % 2 == 1) {
                        out.println(S);
                    } else {
                        out.println(D);
                    }
                }
            }
        }
    }
}
