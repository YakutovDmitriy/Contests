package util;

import java.io.*;
import java.util.StringTokenizer;

public class FastReader extends BufferedReader {

    private StringTokenizer stringTokenizer;

    public FastReader(InputStream inputStream) {
        super(new InputStreamReader(inputStream));
    }

    public FastReader() {
        this(System.in);
    }

    public FastReader(String fileName) throws FileNotFoundException {
        this(new FileInputStream(fileName));
    }

    public FastReader(Reader in, int sz) {
        super(in, sz);
    }

    public FastReader(Reader in) {
        super(in);
    }

    public String nextToken() {
        try {
            while (stringTokenizer == null || !stringTokenizer.hasMoreElements()) {
                stringTokenizer = new StringTokenizer(readLine());
            }
            return stringTokenizer.nextToken();
        } catch (IOException e) {
            return null;
        }
    }

    public String next() {
        return nextToken();
    }

    public String nextLine() {
        try {
            return readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasMoreTokens() {
        if (stringTokenizer != null && stringTokenizer.hasMoreTokens()) {
            return true;
        }
        try {
            String line = readLine();
            stringTokenizer = new StringTokenizer(line);
            return true;
        } catch (IOException | NullPointerException e) {
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