package util.dataStructures;

public class PairLongInt implements Comparable<PairLongInt> {

    public long first;
    public int second;

    public PairLongInt() {}

    public PairLongInt(long first, int second) {
        this.first = first;
        this.second = second;
    }

    public long getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void setFirst(long first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setFirstAndSecond(long first, int second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairIntInt that = (PairIntInt) o;
        return first == that.first && second == that.second;
    }

    public int hashCode() {
        int result = (int) (first ^ (first >>> 32));
        result = 31 * result + second;
        return result;
    }

    public int compareTo(PairLongInt that) {
        if (first != that.first) {
            return Long.compare(first, that.first);
        }
        return Integer.compare(second, that.second);
    }
}