/*
 * @lc app=leetcode id=274 lang=java
 *
 * [274] H-Index
 */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] counts = new int[n + 1];
        for (int num : citations) {
            if (num >= n) {
                counts[n]++;
            } else {
                counts[num]++;
            }
        }
        int cnt = 0;
        // 控制h of papers至少h citations
        for (int i = n; i >= 0; --i) {
            cnt += counts[i];
            if (cnt >= i) {
                return i;
            }
        }
        return 0;
    }
}
// @lc code=end

