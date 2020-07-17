
/*
 * @lc app=leetcode id=164 lang=java
 *
 * [164] Maximum Gap
 */

// @lc code=start
class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        int m = nums[0];
        for (int i = 1; i < n; ++i) {
            m = Math.max(m, nums[i]);
        }
        int exp = 1;
        int R = 10;
        int[] aux = new int[n];
        while (m / exp > 0) {
            int[] count = new int[R];
            for (int i = 0; i < n; ++i) {
                count[(nums[i] / exp) % 10]++;
            }
            for (int i = 1; i < R; ++i) {
                count[i] += count[i - 1];
            }
            for (int i = n - 1; i >= 0; --i) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }
            for (int i = 0; i < n; ++i) {
                nums[i] = aux[i];
            }
            exp *= 10;
        }
        int max = 0;
        for (int i = 1; i < n; ++i) {
            max = Math.max(max, aux[i] - aux[i - 1]);
        }
        return max;
    }
}
// @lc code=end

