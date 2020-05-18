/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 */

// @lc code=start
class Solution {
    public void sortColors(int[] nums) {
        // 三路快排
        if (nums == null || nums.length == 0) {
            return;
        }
        // [0, zero]:0  (zero, one]:1  [two, nums.length - 1]:2
        int zero = -1, one = 0, two = nums.length;
        while (one < two) {
            if (nums[one] == 1) {
                one++;
            } else if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap(nums, --two, one);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end

