package util.dataStructures.strings;

import util.FastReader;
import util.FastWriter;

import java.io.IOException;
import java.io.PrintStream;

public class SuffixArrayLinear {

    void solve() throws IOException {

    }

    static FastReader in;
    static FastWriter out;
    static PrintStream err;

    public static void main(String[] args) {
        try {
            in = new FastReader(System.in);
            out = new FastWriter(System.out);
            err = System.err;
            new SuffixArrayLinear().solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
