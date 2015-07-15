package util.dataStructures;

import static util.math.MathUtils.gcd;

public class QueueGCDInt {

    private final int capacity;
    private int[] queue;
    private int[] gcd;
    private int first;
    private int second;

    public QueueGCDInt(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        gcd = new int[capacity];
        first = -1;
        second = capacity;
    }

    public void push(int value) {
        queue[++first] = value;
        gcd[first] = first == 0 ? value : gcd(value, gcd[first - 1]);
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

    public int getGCD() {
        int ret = 0;
        if (first > -1) {
            ret = gcd[first];
        }
        if (second < capacity) {
            return gcd(ret, gcd[second]);
        }
        return ret;
    }

    private void balance() {
        if (second == capacity) {
            for (int i = first; i >= 0; i--) {
                queue[--second] = queue[i];
                gcd[second] = queue[i];
                if (second < capacity - 1) {
                    gcd[second] = gcd(gcd[second], gcd[second + 1]);
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
