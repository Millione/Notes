class BinaryIndexedTree {

    private long[] sums1;
    private long[] sums2;
    private int n;

    public BinaryIndexedTree(int[] array) {
        n = array.length - 1;
        sums1 = new long[n + 1];
        sums2 = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sums1[i] += array[i];
            sums2[i] += i * array[i];
            int j = i + lowbit(i);
            if (j <= n) {
                sums1[j] += sums1[i];
                sums2[j] += sums2[i];
            }
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    public void update(int x, long add) {
        long add1 = x * add;
        while (x <= n) {
            sums1[x] += add;
            sums2[x] += add1;
            x += lowbit(x);
        }
    }

    public long query(int x) {
        long ret = 0;
        long x1 = x;
        while (x != 0) {
            ret += sums1[x] * (x1 + 1);
            ret -= sums2[x];
            x -= lowbit(x);
        }
        return ret;
    }

    public void updateRange(int x, int y, long add) {
        update(x, add);
        update(y + 1, -add);
    }

    public long queryRange(int x, int y) {
        return query(y) - query(x - 1);
    }

}