/*
 * @lc app=leetcode id=34 lang=java
 *
 * [34] Find First and Last Position of Element in Sorted Array
 */

// @lc code=start
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l1 = 0, r1 = nums.length - 1;
        int[] res = {-1, -1};
        while (l1 <= r1) {
            int mid = l1 + (r1 - l1) / 2;
            if (nums[mid] >= target) {
                r1 = mid - 1;
            } else {
                l1 = mid + 1;
            }
        }
        if (l1 <= nums.length - 1 && nums[l1] == target) {
            res[0] = l1;
        }
        int l2 = 0, r2 = nums.length - 1;
        while(l2 <= r2){
            int mid = l2 + (r2 - l2) / 2;
            if (nums[mid] <= target) {
                l2 = mid + 1;
            } else {
                r2 = mid - 1;
            }
        }
        if (r2 >= 0 && nums[r2] == target) {
            res[1] = r2;
        }
        return res;
    }
}
// @lc code=end

