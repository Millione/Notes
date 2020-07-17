/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

// @lc code=start
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = -1;
        int sum = 0;
        int res = nums.length + 1;
        while (i < nums.length) {
            if (j + 1 < nums.length && sum < s) {
                sum += nums[++j];
            } else {
                sum -= nums[i++];
            }
            if (sum >= s) {
                res = Math.min(res, j - i + 1);
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }
}
// @lc code=end

