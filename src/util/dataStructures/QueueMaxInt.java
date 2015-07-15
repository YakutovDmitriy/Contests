package util.dataStructures;

public class QueueMaxInt {

    private int capacity;
    private int[] queue;
    private int[] max;
    private int first;
    private int second;

    public QueueMaxInt(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        max = new int[capacity];
        first = -1;
        second = capacity;
    }

    public void push(int value) {
        queue[++first] = value;
        max[first] = value;
        if (first > 0 && max[first - 1] > value) {
            max[first] = max[first - 1];
        }
    }

    public int capacity() {
        return capacity;
    }

    public int size() {
        return first + 1 + (capacity - second);
    }

    public boolean isEmpty() {
        return first == -1 && second == capacity;
    }

    public int getMax() {
        int ret = Integer.MAX_VALUE;
        if (first > -1) {
            ret = max[first];
        }
        if (second < capacity) {
            return Math.max(ret, max[second]);
        }
        return ret;
    }

    private void balance() {
        if (second == capacity) {
            for (int i = first; i >= 0; i--) {
                queue[--second] = queue[i];
                max[second] = queue[i];
                if (second < capacity - 1 && max[second + 1] > max[second]) {
                    max[second] = max[second + 1];
                }
            }
            first = -1;
        }
    }

    public int pop() {
        balance();
        return queue[second++];
    }

    public int peek() {
        balance();
        return queue[second];
    }
}
