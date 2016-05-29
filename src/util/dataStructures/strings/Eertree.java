package util.dataStructures.strings;

public class Eertree {

    public int[] len;
    public int[] link;
    public int[][] next;
    public int[] a;
    public int size;
    public int stringLength;
    public int last = 0;

    public Eertree(int capacity, int alphabet) {
        capacity += 3;
        len = new int[capacity];
        link = new int[capacity];
        next = new int[capacity][alphabet];
        a = new int[capacity];
        stringLength = 0;
        a[stringLength++] = alphabet;
        size = 0;
        getNode(1, 0);
        getNode(0, -1);
    }

    private int getNode(int link, int len) {
        this.link[size] = link;
        this.len[size] = len;
        return size++;
    }

    public void append(int letter) {
        a[stringLength++] = letter;
        last = getLetterLink(last);
        if (next[last][letter] == 0) {
            next[last][letter] = getNode(
                    next[getLetterLink(link[last])][letter],
                    len[last] + 2
            );
        }
        last = next[last][letter];
    }

    private int getLetterLink(int v) {
        while (a[stringLength - 1] != a[stringLength - len[v] - 2]) {
            v = link[v];
        }
        return v;
    }

    public int size() {
        return size;
    }
}
