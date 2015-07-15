package util.dataStructures;

public class PairLongLong implements Comparable<PairLongLong> {

    public long first;
    public long second;

    public PairLongLong() {}

    public PairLongLong(long first, long second) {
        this.first = first;
        this.second = second;
    }

    public long getFirst() {
        return first;
    }

    public long getSecond() {
        return second;
    }

    public void setFirst(long first) {
        this.first = first;
    }

    public void setSecond(long second) {
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
        result = 31 * result + (int) (second ^ (second >>> 32));
        return result;
    }

    public int compareTo(PairLongLong that) {
        if (first != that.first) {
            return Long.compare(first, that.first);
        }
        return Long.compare(second, that.second);
    }
}