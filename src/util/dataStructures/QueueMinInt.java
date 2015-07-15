package util.dataStructures;

public class QueueMinInt {

    private int capacity;
    private int[] queue;
    private int[] min;
    private int first;
    private int second;

    public QueueMinInt(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        min = new int[capacity];
        first = -1;
        second = capacity;
    }

    public void push(int value) {
        queue[++first] = value;
        min[first] = value;
        if (first > 0 && min[first - 1] < value) {
            min[first] = min[first - 1];
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

    public int getMin() {
        int ret = Integer.MAX_VALUE;
        if (first > -1) {
            ret = min[first];
        }
        if (second < capacity) {
            return Math.min(ret, min[second]);
        }
        return ret;
    }

    private void balance() {
        if (second == capacity) {
            for (int i = first; i >= 0; i--) {
                queue[--second] = queue[i];
                min[second] = queue[i];
                if (second < capacity - 1 && min[second + 1] < min[second]) {
                    min[second] = min[second + 1];
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
