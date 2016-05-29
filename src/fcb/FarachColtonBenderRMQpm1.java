package fcb;

import java.util.Arrays;

public class FarachColtonBenderRMQpm1 implements RangeMinimumQuery {

    private final int MINIMAL_BLOCK_LENGTH = 5;

    private int[] a;
    private int blockLength;
    private int[][][] blockDp;
    private int[] typeOfBlock;
    RangeMinimumQuery rmqOnBlocks;

    @Override
    public void buildRMQ(int[] array) {
        {
            for (int i = 1; i < array.length; i++) {
                if (Math.abs(array[i] - array[i - 1]) != 1) {
                    throw new UnsupportedOperationException("neighbours in the array must have difference 1");
                }
            }
        }
        {
            int log = 0;
            while ((1 << log) < array.length) {
                log++;
            }
            blockLength = Math.max(MINIMAL_BLOCK_LENGTH, log / 2 + 1);
        }
        int blockCount;
        {
            blockCount = (array.length + blockLength - 1) / blockLength + 1;
            a = new int[blockCount * blockLength];
            System.arraycopy(array, 0, a, 0, array.length);
            for (int i = array.length; i < a.length; i++) {
                a[i] = a[i - 1] + 1;
            }
        }
        {
            int masksCount = 1 << (blockLength - 1);
            blockDp = new int[masksCount][blockLength][blockLength + 1];
            for (int mask = 0; mask < masksCount; mask++) {
                int[] cur = new int[blockLength];
                for (int i = 1; i < blockLength; i++) {
                    if (((mask >> (i - 1)) & 1) == 1) {
                        cur[i] = cur[i - 1] + 1;
                    } else {
                        cur[i] = cur[i - 1] - 1;
                    }
                }
                for (int i = 0; i < blockLength; i++) {
                    int min = i;
                    for (int j = i + 1; j <= blockLength; j++) {
                        if (cur[j - 1] < cur[min]) {
                            min = j - 1;
                        }
                        blockDp[mask][i][j] = min;
                    }
                }
            }
        }
        {
            typeOfBlock = new int[blockCount];
            for (int i = 0; i < blockCount; i++) {
                int start = i * blockLength;
                int end = start + blockLength;
                int mask = 0;
                for (int j = end - 1; j > start; j--) {
                    mask <<= 1;
                    mask |= a[j] > a[j - 1] ? 1 : 0;
                }
                typeOfBlock[i] = mask;
            }
        }
        {
            int[] compressed = new int[blockCount];
            for (int i = 0; i < blockCount; i++) {
                int mask = typeOfBlock[i];
                int index = i * blockLength + blockDp[mask][0][blockLength];
                compressed[i] = a[index];
            }
            rmqOnBlocks = new SparseTableRMQ();
            rmqOnBlocks.buildRMQ(compressed);
        }
    }

    @Override
    public int getMinPos(int from, int to) {

        int fromBlock = from / blockLength;
        int fromMask = typeOfBlock[fromBlock];
        int fromStart = fromBlock * blockLength;
        int fromPos = from - fromStart;

        int toBlock = to / blockLength;
        int toMask = typeOfBlock[toBlock];
        int toStart = toBlock * blockLength;
        int toPos = to - toStart;

        if (fromBlock == toBlock) {
            return fromStart + blockDp[fromMask][fromPos][toPos];
        }

        int fromMin = fromStart + blockDp[fromMask][fromPos][blockLength];
        int toMin = toStart + blockDp[toMask][0][toPos];

        if (fromBlock == toBlock - 1) {
            return (toPos == 0 || a[fromMin] <= a[toMin]) ? fromMin : toMin;
        }

        int betweenBlock = rmqOnBlocks.getMinPos(fromBlock + 1, toBlock);
        int betweenStart = betweenBlock * blockLength;
        int betweenMask = typeOfBlock[betweenBlock];
        int betweenMin = betweenStart + blockDp[betweenMask][0][blockLength];

        int answer = fromMin;
        if (a[betweenMin] < a[answer]) {
            answer = betweenMin;
        }
        if (toPos > 0 && a[toMin] < a[answer]) {
            answer = toMin;
        }
        return answer;
    }

    @Override
    public String getName() {
        return "farach  ±1";
    }
}
