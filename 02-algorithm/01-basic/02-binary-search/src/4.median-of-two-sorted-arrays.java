/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int l = 0, r = n1 - 1, mid1, mid2;
        // 偶数-->右中值  奇数-->中值
        int k = (n1 + n2 + 1) / 2;
        while (l <= r) {
            mid1 = l + (r - l) / 2;
            mid2 = k - mid1;
            // mid2可能等于n2
            if (nums1[mid1] < nums2[mid2 - 1]) {
                l = mid1 + 1;
            } else {
                r = mid1 - 1;
            }
        }
        mid1 = l;
        mid2 = k - mid1;
        // 注意边界和越界情况
        int c1 = Math.max(mid1 <= 0 ? Integer.MIN_VALUE : nums1[mid1 - 1]
                        , mid2 <= 0 ? Integer.MIN_VALUE : nums2[mid2 - 1]);
        if ((n1 + n2) % 2 == 1) {
            return (double)c1;
        }
        int c2 = Math.min(mid1 >= n1 ? Integer.MAX_VALUE : nums1[mid1]
                        , mid2 >= n2 ? Integer.MAX_VALUE : nums2[mid2]);
        return (c1 + c2) / 2.0;
    }
}
// @lc code=end

