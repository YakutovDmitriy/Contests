package fcb;

import java.util.Arrays;

public class StupidRMQ implements RangeMinimumQuery {

    private int[] array;

    public void buildRMQ(int[] array) {
        this.array = array.clone();
    }

    @Override
    public int getMinPos(int from, int to) {
        int ret = from;
        for (int i = from; i < to; i++) {
            if (array[i] < array[ret]) {
                ret = i;
            }
        }
        return ret;
    }

    @Override
    public String getName() {
        return "stupid rmq";
    }
}
