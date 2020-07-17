/*
 * @lc app=leetcode id=74 lang=java
 *
 * [74] Search a 2D Matrix
 */

// @lc code=start
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1, mid, val;
        while (l <= r) {
            mid = l + (r - l) / 2;
            // 二维数组中位置
            val = matrix[mid / n][mid % n];
            if (val > target) {
                r = mid - 1;
            } else if (val < target) {
                l = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end

