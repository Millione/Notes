/*
 * @lc app=leetcode id=88 lang=java
 *
 * [88] Merge Sorted Array
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] >= nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }
}
// @lc code=end

