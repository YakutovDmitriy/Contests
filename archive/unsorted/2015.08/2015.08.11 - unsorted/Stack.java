package code;

import util.FastReader;
import util.FastWriter;

public class Stack {
    public void solve(int testNumber, FastReader in, FastWriter out) {
        int n = in.nextInt();
        int[] stack = new int[1234567];
        int size = 0;
        stack[size++] = Integer.MAX_VALUE;
        for (int it = 0; it < n; it++) {
            switch (in.nextInt()) {
                case 1:
                    stack[size] = Math.min(stack[size - 1], in.nextInt());
                    size++;
                    break;
                case 2:
                    size--;
                    break;
                case 3:
                    out.println(stack[size - 1]);
            }
        }
    }
}
