package util.dataStructures;

public class PairIntInt implements Comparable<PairIntInt> {

    public int first;
    public int second;

    public PairIntInt() {}

    public PairIntInt(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int[] getFirstAndSecond() {
        return new int[] {first, second};
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setFirstAndSecond(int first, int second) {
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
        return 31 * first + second;
    }

    public int compareTo(PairIntInt that) {
        if (first != that.first) {
            return Integer.compare(first, that.first);
        }
        return Integer.compare(second, that.second);
    }
}