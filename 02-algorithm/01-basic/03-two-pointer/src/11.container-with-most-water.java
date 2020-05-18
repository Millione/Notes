/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int res = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                res = Math.max(res, height[l] * (r - l));
                l++;
            } else {
                res = Math.max(res, height[r] * (r - l));
                r--;
            }
        }
        return res;
    }
}
// @lc code=end

