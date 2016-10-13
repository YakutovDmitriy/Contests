package util;

import util.dataStructures.pairs.PairIntInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomGenerator {

    public static final Random RANDOM = new Random(960172L);
    
    public static int[] randomPermutation(int size) {
        int[] ret = new int[size];
        Arrays.setAll(ret, i -> i);
        for (int i = 0; i < size; i++) {
            int j = RANDOM.nextInt(i + 1);
            int temp = ret[j];
            ret[j] = ret[i];
            ret[i] = temp;
        }
        return ret;
    }

    public static List<PairIntInt> randomTree(int vertices) {
        List<PairIntInt> ret = new ArrayList<>();
        int[] perm = randomPermutation(vertices);
        for (int i = 1; i < vertices; i++) {
            int anc = RANDOM.nextInt(i);
            ret.add(new PairIntInt(perm[i], perm[anc]));
        }
        return ret;
    }

    public static int[] randomArray(int length) {
        int[] ret = new int[length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = RANDOM.nextInt();
        }
        return ret;
    }

    private RandomGenerator() {}
}
