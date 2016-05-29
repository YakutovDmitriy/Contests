package fcb;

import util.dataStructures.pairs.PairIntInt;

import java.util.List;

public interface LCATree {

    void buildTree(List<PairIntInt> edges, int root);

    int getLCA(int a, int b);

    String getName();
}
