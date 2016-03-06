package util;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class FastReader {

    private StringTokenizer tokenizer;
    private BufferedReader reader;

    public FastReader(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String nextToken() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        } catch (IOException e) {
            return null;
        }
    }

    public String next() {
        return nextToken();
    }

    public String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasMoreTokens() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public int nextInt() {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() {
        return Double.parseDouble(nextToken());
    }

    public BigInteger nextBigInteger() {
        return new BigInteger(nextToken());
    }

    public char[] nextCharArray() {
        String next = nextToken();
        if (next == null) {
            return null;
        }
        return next.toCharArray();
    }

    public char[][] nextTable(int size, int length) {
        char[][] ret = new char[size][];
        for (int i = 0; i < size; i++) {
            ret[i] = nextCharArray();
            if (ret[i] == null || ret[i].length != length) {
                return null;
            }
        }
        return ret;
    }

    public int[] nextArrayInt(int size) {
        int[] ret = new int[size];
        for (int i = 0; i < size; i++) {
            ret[i] = nextInt();
        }
        return ret;
    }

    public long[] nextArrayLong(int size) {
        long[] ret = new long[size];
        for (int i = 0; i < size; i++) {
            ret[i] = nextLong();
        }
        return ret;
    }

    public double[] nextArrayDouble(int size) {
        double[] ret = new double[size];
        for (int i = 0; i < size; i++) {
            ret[i] = nextDouble();
        }
        return ret;
    }

}