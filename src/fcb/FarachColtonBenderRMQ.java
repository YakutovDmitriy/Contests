package fcb;

import java.util.Arrays;

public class FarachColtonBenderRMQ implements RangeMinimumQuery {

    private RMQLCATree tree;

    @Override
    public void buildRMQ(int[] array) {
        int n = array.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] anc = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        Arrays.fill(anc, -1);
        for (int i = 1; i < n; i++) {
            int cur = i - 1;
            while (anc[cur] >= 0 && array[cur] > array[i]) {
                cur = anc[cur];
            }
            if (array[cur] > array[i]) {
                anc[cur] = i;
                left[i] = cur;
            } else {
                left[i] = right[cur];
                if (left[i] >= 0) {
                    anc[left[i]] = i;
                }
                right[cur] = i;
                anc[i] = cur;
            }
        }
//        int[][] adjacents = new int[n][];
//        int root = -1;
//        for (int i = 0; i < n; i++) {
//            int deg = 0;
//            if (anc[i] >= 0) deg++;
//            if (left[i] >= 0) deg++;
//            if (right[i] >= 0) deg++;
//            adjacents[i] = new int[deg];
//            deg = 0;
//            if (anc[i] >= 0) adjacents[i][deg++] = anc[i];
//            if (left[i] >= 0) adjacents[i][deg++] = left[i];
//            if (right[i] >= 0) adjacents[i][deg++] = right[i];
//            if (anc[i] < 0) {
//                root = i;
//            }
//        }
        int[][] adjacents = new int[n][2];
        int root = -1;
        for (int i = 0; i < n; i++) {
            adjacents[i][0] = left[i];
            adjacents[i][1] = right[i];
            if (anc[i] < 0) {
                root = i;
            }
        }
        tree = new RMQLCATree();
        tree.buildTree(adjacents, root);
    }

    @Override
    public int getMinPos(int from, int to) {
        return tree.getLCA(from, to - 1);
    }

    @Override
    public String getName() {
        return "farach rmq";
    }
}
