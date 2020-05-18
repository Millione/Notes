class BinaryIndexedTree {
    
    private int[] sums;
    private int n;
    // 数组array下标从1开始
    public BinaryIndexedTree(int[] array) {
        n = array.length - 1;
        sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] += array[i];
            int j = i + lowbit(i);
            if (j <= n) {
                sums[j] += sums[i];
            }
        }
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }
    
    public void update(int x, int add) {
        while (x <= n) {
            sums[x] += add;
            x += lowbit(x);
        }
    }
    
    public int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += sums[x];
            x -= lowbit(x);
        }
        return ret;
    }
    
    public int queryRange(int x, int y) {
        return query(y) - query(x - 1);
    }
    
}