package fcb;

public interface RangeMinimumQuery {

    void buildRMQ(int[] array);

    int getMinPos(int from, int to);

    String getName();
}
