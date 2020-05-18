import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=57 lang=java
 *
 * [57] Insert Interval
 */

// @lc code=start
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            if (newInterval == null || newInterval.length == 0) {
                return res.toArray(new int[0][]);
            } else {
                res.add(newInterval);
                return res.toArray(new int[0][]);
            }
        } else {
            if (newInterval == null || newInterval.length == 0) {
                return intervals;
            }
        }
        int i = 0;
        // 从左至右
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i++]);
        }
        int left = newInterval[0], right = newInterval[1];
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            left = Math.min(left, intervals[i][0]);
            right = Math.max(right, intervals[i][1]);
            ++i;
        }
        res.add(new int[]{left, right});
        while (i < intervals.length) {
            res.add(intervals[i++]);
        }
        return res.toArray(new int[0][]);
    }
}
// @lc code=end

