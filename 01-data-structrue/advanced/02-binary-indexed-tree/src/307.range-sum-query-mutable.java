/*
 * @lc app=leetcode id=307 lang=java
 *
 * [307] Range Sum Query - Mutable
 */

// @lc code=start
class NumArray {

    private int[] sums;
    private int[] nums;
    private int n;

    public NumArray(int[] array) {
        n = array.length;
        nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = array[i - 1];
        }
        sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] += nums[i];
            int j = i + lowbit(i);
            if (j <= n) {
                sums[j] += sums[i];
            }
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    public void update(int x, int val) {
        x++;
        int add = val - nums[x];
        nums[x] = val;
        while (x <= n) {
            sums[x] += add;
            x += lowbit(x);
        }
    }
    
    public int sumRange(int x, int y) {
        x++;
        y++;
        return query(y) - query(x - 1);
    }

    private int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += sums[x];
            x -= lowbit(x);
        }
        return ret;
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
// @lc code=end

